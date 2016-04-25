define([
    'backbone'
], function(Backbone){

    var LocalidadModel = Backbone.Model.extend({

        urlRoot: 'localidad',

        defaults: {
            idLocalidad: '',
            localidad: ''
        },

        initialize: function() {
        }

    });

	return LocalidadModel;
});