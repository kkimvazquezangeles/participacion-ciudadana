define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/PerfilModel',
    'models/FileModel',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/FilesCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html',
    'Session'
], function($, Backbone, BaseView, PerfilModel, FileModel,
            ModalGenericView, FilesView, FilesCollection,
            tplPerfilAdmin, Session){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-upload'         : 'uploadFile',
            'click .menu-action'        : 'opcionMenu',
            'click #btn-exit'           : 'logout',
            'change #file-es'           : 'changeFile'
        },

        initialize: function() {
            this.model = new PerfilModel();
            this.model.set({id: Session.get('username')});
            this.files = new FilesCollection();
            this.listenTo(this.files, 'add', this.agregarFile);
            this.listenTo(this.files, 'sync', this.syncFiles);

            this.listenTo(this.model, 'sync', this.syncPerfil);
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        uploadFile: function(){
            this.$el.find('#file-es').click();
        },

        changeFile: function(event) {
            var that = this;
            var fileup = $('#file-es')[0].files[0];
            var data = new FormData();
            data.append('fileupload', fileup);
            data.append('idDepto', this.model.get('deptoId'));
            data.append('username', this.model.get('username'));

            $.ajax({
                url: 'file/upload/generic',
                data: data,
                cache: false,
                contentType: false,
                processData: false,
                type: 'POST',
                success: function(data){
                    var fileModel = new FileModel(data);
                    alert('Operación realizada con éxito');
                    that.files.add(fileModel);
                },
                error: function(data){
                    alert('Se presentó un error, vuelva a intentar');
                }
            });
        },

        agregarFile: function(modelo){
            var vista = new FilesView(modelo);
            $("#grid-data").find('tbody:last').append(vista.render().$el);
        },

        syncFiles: function(){
        },

        syncPerfil: function(){
            this.$el.html(this.template(this.model.toJSON()));

            var roles = Session.get('roles');
            if(roles[0] !== 'ADMIN'){
                $('#menu-container').hide();
                $('#btn-upload').show();
                this.files.setIdDepto(this.model.get('deptoId'));
                this.files.fetch();
            } else {
                $('#grid-data').hide();
                $('#btn-upload').hide();
            }
        },

        opcionMenu: function(event) {
            $('#grid-data').show();
            this.files.reset();
            $("#grid-data").find('tbody').html('');
            $('.menu-action').parent().removeClass('menu-select');
            $(event.currentTarget).parent().addClass('menu-select');
            this.files.setIdDepto($(event.currentTarget).attr('value'));
            this.files.fetch();
        },

        logout: function(){
            Session.logout(function(response){
                Backbone.history.navigate('', { trigger : true });
            });
        }

    });

    return PerfilAdminView;

});