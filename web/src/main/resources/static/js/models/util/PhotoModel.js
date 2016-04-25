define([
    'backbone'
], function(Backbone){

    var PhotoModel = Backbone.Model.extend({

        urlRoot: 'photo',

        defaults: {
        },

        initialize: function() {
        }

    });

	return PhotoModel;
});