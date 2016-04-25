define([
	'jquery',
	'underscore',
	'core/BaseView',
	'dateformat',
	'text!templates/private/perfil/tplRowFile.html'
], function($, _, BaseView, dateformat, tplRowFile){

	var FilesView = BaseView.extend({
        template: _.template(tplRowFile),
        tagName: 'tr',

        events: {
            'click #btn-delete'     : 'deleteFile',
            'click #btn-download'   : 'downloadFile'
        },

        initialize: function(modelo) {
            this.model = modelo;
            var fechaRegistroDes = new Date(this.model.get('fechaRegistro'));
            this.model.set({fechaRegistroDes: fechaRegistroDes.format("mm/dd/yyyy")});
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deleteFile: function(event){
            var res = confirm("¿Desea eliminar este archivo?");
            if(res){
                that = this;
                this.model.destroy({ contentType: 'application/json',
                wait:true,
                    success: function(model, response) {
                        that.destroyView();
                        alert(response.message);
                    },
                    error: function(model, error) {
                        alert(error);
                    }
                });
            }
        },

        downloadFile: function(){
        }

	});

	return FilesView;

});