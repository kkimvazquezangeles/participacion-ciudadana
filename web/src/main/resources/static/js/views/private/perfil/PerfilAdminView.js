define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/PerfilModel',
    'models/FileModel',
    'models/MunicipioModel',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/MunicipiosPropuestaCollection',
    'collections/LocalidadesPropuestaCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html',
    'Session'
], function($, Backbone, BaseView, PerfilModel, FileModel, MunicipioModel,
            ModalGenericView, FilesView, MunicipiosPropuestaCollection, LocalidadesPropuestaCollection,
            tplPerfilAdmin, Session){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-exit'           : 'logout',
            'change #select-municipios' : 'cambiarMunicipio'
        },

        initialize: function() {
            this.model = new MunicipioModel();
            Backbone.Validation.bind(this);

            this.municipios = new MunicipiosPropuestaCollection();
            this.listenTo(this.municipios, 'add', this.agregarMunicipio);
            this.listenTo(this.municipios, 'sync', this.syncMunicipio);
            this.localidades = new LocalidadesPropuestaCollection();
            this.listenTo(this.localidades, 'add', this.agregarLocalidad);
            this.listenTo(this.localidades, 'sync', this.syncLocalidad);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.municipios.fetch();
            return this;
        },

        logout: function(){
            Session.logout(function(response){
                Backbone.history.navigate('', { trigger : true });
            });
        },

        agregarMunicipio: function(modelo){
            $('#select-municipios').append($('<option>', {
                value: modelo.get('idMunicipio'),
                text : modelo.get('municipio')
            }));
        },

        syncMunicipio: function(){
            $('#select-municipios').change();
        },

        agregarLocalidad: function(modelo){
            $('#select-localidades').append($('<option>', {
                value: modelo.get('idLocalidad'),
                text : modelo.get('localidad')
            }));
        },

        syncLocalidad: function(){
        },

        cambiarMunicipio: function(event) {
            var idMunicipio = $(event.target).val();
            this.localidades.setIdMunicipio(idMunicipio);
            this.localidades.fetch();

        }

    });

    return PerfilAdminView;

});