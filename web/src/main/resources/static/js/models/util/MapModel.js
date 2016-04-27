define([
    'backbone'
], function(Backbone){

    var MapModel = Backbone.Model.extend({

        urlRoot: 'map',

        defaults: {
        },

        initialize: function() {
        }

    });

	return MapModel;
});