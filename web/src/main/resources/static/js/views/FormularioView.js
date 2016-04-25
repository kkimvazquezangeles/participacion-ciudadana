define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/PropuestaModel',
    'collections/MunicipiosCollection',
    'collections/LocalidadesCollection',
	'text!templates/tplFormulario.html',
	'Session'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, PropuestaModel, MunicipiosCollection,
    LocalidadesCollection, tplFormulario, Session){

	var FormularioView = BaseView.extend({
        template: _.template(tplFormulario),

        events: {
            'click #btn-enviar': 'enviar',
            'click #btn-cancelar': 'cancelar',
            'click #btn-consultar': 'consultar',
            'change #select-municipios': 'cambiarMunicipio'
        },

        initialize: function() {
            this.model = new PropuestaModel();

            this.municipios = new MunicipiosCollection();
            this.listenTo(this.municipios, 'add', this.agregarMunicipio);
            this.listenTo(this.municipios, 'sync', this.syncMunicipio);
            this.localidades = new LocalidadesCollection();
            this.listenTo(this.localidades, 'add', this.agregarLocalidad);
            this.listenTo(this.localidades, 'sync', this.syncLocalidad);

            this.model.once("sync", this.savePropuestaSuccess);
            this.model.once("error", this.savePropuestaError);
            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.municipios.fetch();
            return this;
        },

        enviar: function() {
            var data = this.$el.find("#form-2").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        cancelar: function() {

        },

        consultar: function() {
            Backbone.history.navigate('admin', { trigger : true });
        },

        savePropuestaSuccess: function(model, response, options){
            alert("Success");
        },

        savePropuestaError: function(model, response, options){
            alert("Error");
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

	return FormularioView;

});