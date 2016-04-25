define([
    'backbone'
], function(Backbone){

    var ModalGenericModel = Backbone.Model.extend({

        urlRoot: 'modal',

        defaults: {
        },

        initialize: function() {
        }

    });

	return ModalGenericModel;
});