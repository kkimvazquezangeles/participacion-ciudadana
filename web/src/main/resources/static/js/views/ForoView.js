define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/PropuestaModel',
    'collections/MunicipiosCollection',
    'collections/LocalidadesCollection',
	'text!templates/tplForo.html',
	'Session'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, PropuestaModel, MunicipiosCollection,
    LocalidadesCollection, tplForo, Session){

	var ForoView = BaseView.extend({
        template: _.template(tplForo),

        events: {
            'click #btn-enviar': 'enviar',
            'click #btn-cancelar': 'cancelar',
            'click #btn-consultar': 'salir',
            'change #select-municipios': 'cambiarMunicipio'
        },

        initialize: function() {
            this.model = new PropuestaModel();
            Backbone.Validation.bind(this);

            this.municipios = new MunicipiosCollection();
            this.listenTo(this.municipios, 'add', this.agregarMunicipio);
            this.listenTo(this.municipios, 'sync', this.syncMunicipio);
            this.localidades = new LocalidadesCollection();
            this.listenTo(this.localidades, 'add', this.agregarLocalidad);
            this.listenTo(this.localidades, 'sync', this.syncLocalidad);

            this.model.once("error", this.savePropuestaError);
            this.model.once("sync", this.savePropuestaSuccess);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.municipios.fetch();
            return this;
        },

        enviar: function() {
            var data = this.$el.find(".form-2").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
                $('input[name=nombre]').val('');
                $('input[name=materno]').val('');
                $('input[name=paterno]').val('');
                $('input[name=correo]').val('');
                $('input[name=telefono]').val('');
                $('input[name=propuesta5]').val('');
                alert("Gracias por tu opinión");
            } else {
                $('input[name=nombre]').addClass('has-error');
                $('input[name=paterno]').addClass('has-error');
                $('input[name=materno]').addClass('has-error');
                $('input[name=propuesta5]').addClass('has-error');
                $('select[name=idLocalidad]').addClass('has-error');

            }
        },

        cancelar: function() {
                $('input[name=nombre]').removeClass('has-error');
                $('input[name=paterno]').removeClass('has-error');
                $('input[name=materno]').removeClass('has-error');
                $('input[name=propuesta5]').removeClass('has-error');
                $('select[name=idLocalidad]').removeClass('has-error');
                $('input[name=nombre]').val('');
                $('input[name=materno]').val('');
                $('input[name=paterno]').val('');
                $('input[name=correo]').val('');
                $('input[name=telefono]').val('');
                $('input[name=propuesta5]').val('');

        },

        salir: function() {
            Backbone.history.navigate('', { trigger : true });
        },

        savePropuestaSuccess: function(model, response, options){
            alert("Gracias por tu opinión");
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
            $('#select-localidades').empty();
            this.localidades.fetch();

        }


	});

	return ForoView;

});