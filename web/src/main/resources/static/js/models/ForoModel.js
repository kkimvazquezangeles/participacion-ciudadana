define([
    'backbone'
], function(Backbone){

    var ForoModel = Backbone.Model.extend({

        urlRoot: 'foro',

        defaults: {
            idForo: '',
            foro: ''
        },

        initialize: function() {
        }

    });

	return ForoModel;
});