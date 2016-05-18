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
            propuesta4: '',
            idForo:'',
            idTema:'',
            propuesta5: ''

        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true,
                msg: 'Por favor indica cual es tu nombre'
            },
            idMunicipio: {
                required: true,
                msg: 'Selecciona tu municipio'
            },
            idLocalidad: {
                required: true,
                msg: 'Selecciona tu localidad'
            }                           
        }

    });

	return PropuestaModel;
});