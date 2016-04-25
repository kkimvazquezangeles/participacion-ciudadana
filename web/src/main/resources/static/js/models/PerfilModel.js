define([
    'backbone'
], function(Backbone){

    var PerfilModel = Backbone.Model.extend({

        urlRoot: 'persona',

        defaults: {
            deptoDes: '',
            nombreCompleto: ''
        },

        initialize: function() {
        }

    });

	return PerfilModel;
});