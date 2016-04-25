define([
    'backbone'
], function(Backbone){

    var MunicipioModel = Backbone.Model.extend({

        urlRoot: 'municipio',

        defaults: {
            idMunicipio: '',
            municipio: ''
        },

        initialize: function() {
        }

    });

	return MunicipioModel;
});