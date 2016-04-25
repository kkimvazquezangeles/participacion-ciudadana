define([
    'backbone'
], function(Backbone){

    var PropuestaModel = Backbone.Model.extend({

        urlRoot: 'propuesta',

        defaults: {
            nombre: '',
            paterno: '',
            materno: '',
            correo: '',
            telefono: '',
            idMunicipio: '',
            idLocalidad: '',
            propuesta1: '',
            propuesta2: '',
            propuesta3: '',
            propuesta4: ''

        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.nombre) {
                return 'Captura tu nombre completo.';
            }
            if(!atributos.propuesta1) {
                return 'Debes realizar al menos una propuesta.';
            }
            if(!atributos.idMunicipio) {
                return 'Selecciona tu municipio.';
            }
            if(!atributos.idLocalidad) {
                return 'Selecciona tu localidad.';
            }
        }

    });

	return PropuestaModel;
});