define([
	'jquery',
	'backbone',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/util/tplUploadView.html'
], function($, Backbone, BaseView, ModalGenericView, tplUploadView){

    var UploadFileView = BaseView.extend({
        template: _.template(tplUploadView),

        events: {
            'click #btn-upload'     : 'uploadFile',
            'click #btn-confirm'    : 'confirmFile',
            'click #btn-cancel'     : 'cancelFile',
            'click #btn-delete'     : 'deleteFile',
            'change #file-es'       : 'changeFile'
        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.callbackUpload = opts.callbackUpload;
            this.callbackDelete = opts.callbackDelete;
            this.urlUpload = opts.urlUpload;
            this.urlDelete = opts.urlDelete;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.find('#file-es').hide();
            this.btnUpload = this.$el.find('#btn-upload');
            this.btnConfirm = this.$el.find('#btn-confirm');
            this.btnCancel = this.$el.find('#btn-cancel');
            this.btnDelete = this.$el.find('#btn-delete');
            this.previewPhoto =  this.$el.find('#preview-photo');
            this.setUp('Empty');
            return this;
        },

        setUp: function(type) {
            if (!this.model.get('hasLogo')) {
                this.btnDelete.hide();
            } else {
                this.btnDelete.show();
            }
            if (type=='Empty') {
                this.btnConfirm.hide();
                this.btnCancel.hide();
                this.btnUpload.show();
            } else {
                this.btnConfirm.show();
                this.btnCancel.show();
                this.btnUpload.hide();
                this.btnDelete.hide();
            }
        },

        uploadFile: function(){
            this.$el.find('#file-es').click();
        },

        deleteFile: function(){
            var data = new FormData();
            data.append('key', this.model.get('idLogo'));
            data.append('logo', this.model.get('nameLogo'));
            data.append('origin', this.model.get('type'));
            this.servicesRemote(this.urlDelete, data, this.callbackDelete);
        },

        confirmFile: function(){
            var picture = $('#file-es')[0].files[0];
            var data = new FormData();
            data.append('filelogo', picture);
            data.append('id', this.model.get('idLogo'));
            data.append('origin', this.model.get('type'));
            this.servicesRemote(this.urlUpload, data, this.callbackUpload);
        },

        servicesRemote: function(url, data, callback) {
            var that = this;
            $.ajax({
                url: url,
                data: data,
                cache: false,
                contentType: false,
                processData: false,
                type: 'POST',
                success: function(data){
                    if (this.url == that.urlDelete){
                        that.model.set({hasLogo: false});
                        data.defaultname += that.makeId();
                        that.previewPhoto.attr('src', data.defaultname);
                    } else {
                        that.model.set({hasLogo: true});
                        data.pathfilename += that.makeId();
                    }
                    callback(data);
                    that.setUp('Empty');
                    new ModalGenericView({message: 'Operación realizada con éxito'});
                },
                error: function(data){
                    new ModalGenericView({message: 'Se presentó un error, vuelva a intentar'});
                }
            });
        },

        cancelFile: function(){
            this.previewPhoto.attr('src', this.model.get('pathLogo'));
            this.setUp('Empty');
        },

        changeFile: function(event) {
            var that = this;
            var reader = new FileReader();
            reader.onload = function (event) {
                that.previewPhoto.attr('src', event.target.result);
                that.setUp('Full');
            };
            reader.readAsDataURL(event.target.files[0]);
        },

        makeId: function()
        {
            var text = "?";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

            for( var i=0; i < 5; i++ )
                text += possible.charAt(Math.floor(Math.random() * possible.length));

            return text;
        }
    });

    return UploadFileView;
});