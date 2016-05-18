define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/PropuestaModel',
    'collections/MunicipiosCollection',
    'collections/LocalidadesCollection',
    'collections/ForosCollection',
    'collections/TemasCollection',
	'text!templates/tplFormulario.html',
	'Session'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, PropuestaModel, MunicipiosCollection,
    LocalidadesCollection, ForosCollection, TemasCollection, tplFormulario, Session){

	var FormularioView = BaseView.extend({
        template: _.template(tplFormulario),

        events: {
            'click #btn-enviar': 'enviar',
            'click #btn-cancelar': 'cancelar',
            'click #btn-consultar': 'consultar',
            'change #select-municipios': 'cambiarMunicipio',
            'change #select-foros1': 'cambiarForo1',
            'change #select-foros2': 'cambiarForo2',
            'change #select-foros3': 'cambiarForo3',
            'change #select-foros4': 'cambiarForo4'
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

            this.foros = new ForosCollection();
            this.listenTo(this.foros, 'add', this.agregarForo);
            this.listenTo(this.foros, 'sync', this.syncForo);
            this.temas = new TemasCollection();
            this.listenTo(this.temas, 'add', this.agregarTema);
            this.listenTo(this.temas, 'sync', this.syncTema);

            this.model.once("error", this.savePropuestaError);
            this.model.once("sync", this.savePropuestaSuccess);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.municipios.fetch();
            this.foros.fetch();
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
                $('input[name=propuesta1]').val('');
                $('input[name=propuesta2]').val('');
                $('input[name=propuesta3]').val('');
                $('input[name=propuesta4]').val('');
                alert("Gracias por tu opinión");
            } else {
                $('input[name=nombre]').addClass('has-error');
                $('input[name=paterno]').addClass('has-error');
                $('input[name=materno]').addClass('has-error');
                $('input[name=propuesta1]').addClass('has-error');
                $('select[name=idLocalidad]').addClass('has-error');

            }
        },

        cancelar: function() {
                $('input[name=nombre]').removeClass('has-error');
                $('input[name=paterno]').removeClass('has-error');
                $('input[name=materno]').removeClass('has-error');
                $('input[name=propuesta1]').removeClass('has-error');
                $('select[name=idLocalidad]').removeClass('has-error');
                $('input[name=nombre]').val('');
                $('input[name=materno]').val('');
                $('input[name=paterno]').val('');
                $('input[name=correo]').val('');
                $('input[name=telefono]').val('');
                $('input[name=propuesta1]').val('');
                $('input[name=propuesta2]').val('');
                $('input[name=propuesta3]').val('');
                $('input[name=propuesta4]').val('');

        },

        consultar: function() {
            Backbone.history.navigate('admin', { trigger : true });
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

        },

        agregarForo: function(modelo){
            $('#select-foros1').append($('<option>', {
                value: modelo.get('idForo'),
                text : modelo.get('foro')
            }));
            $('#select-foros2').append($('<option>', {
                value: modelo.get('idForo'),
                text : modelo.get('foro')
            }));
            $('#select-foros3').append($('<option>', {
                value: modelo.get('idForo'),
                text : modelo.get('foro')
            }));
            $('#select-foros4').append($('<option>', {
                value: modelo.get('idForo'),
                text : modelo.get('foro')
            }));
        },

        syncForo: function(){
            this.first = true;
            $('#select-foros1').change();
            //$('#select-foros2').change();
            //$('#select-foros3').change();
            //$('#select-foros4').change();
        },

        agregarTema: function(modelo){
            var selecttema='#select-temas1';
            if (this.temaselect === 2){
                selecttema='#select-temas2';
            } else if(this.temaselect === 3){
                selecttema='#select-temas3';
            } else if(this.temaselect === 4){
                selecttema='#select-temas4';
            }

            $(selecttema).append($('<option>', {
                value: modelo.get('idTema'),
                text : modelo.get('tema')
            }));
        },

        syncTema: function(){
            if (this.first === true && this.temaselect === 1) {
                $('#select-foros2').change();
            } else if (this.first === true && this.temaselect === 2){
                $('#select-foros3').change();
            } else if (this.first === true && this.temaselect === 3){
                $('#select-foros4').change();
            } else if (this.first === true && this.temaselect === 4){
                this.first = false;
            }
        },

        cambiarForo1: function(event) {
            var idForo1 = $(event.target).val();
            this.temas.setIdForo(idForo1);
            $('#select-temas1').empty();
            this.temas.fetch();
            this.temaselect = 1;
        },

        cambiarForo2: function(event) {
            var idForo2 = $(event.target).val();
            this.temas.setIdForo(idForo2);
            $('#select-temas2').empty();
            this.temas.fetch();
            this.temaselect = 2;
        },

        cambiarForo3: function(event) {
            var idForo3 = $(event.target).val();
            this.temas.setIdForo(idForo3);
            $('#select-temas3').empty();
            this.temas.fetch();
            this.temaselect = 3;
        },

        cambiarForo4: function(event) {
            var idForo4 = $(event.target).val();
            this.temas.setIdForo(idForo4);
            $('#select-temas4').empty();
            this.temas.fetch();
            this.temaselect = 4;
        }

	});

	return FormularioView;

});