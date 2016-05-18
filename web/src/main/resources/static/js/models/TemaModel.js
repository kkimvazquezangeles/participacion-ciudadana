define([
    'backbone'
], function(Backbone){

    var TemaModel = Backbone.Model.extend({

        urlRoot: 'tema',

        defaults: {
            idTema: '',
            tema: ''
        },

        initialize: function() {
        }

    });

	return TemaModel;
});