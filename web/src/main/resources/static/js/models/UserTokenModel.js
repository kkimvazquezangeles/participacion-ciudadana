define([
    'backbone'
], function(Backbone){

    var UserTokenModel = Backbone.Model.extend({

        urlRoot: 'usertoken',

        defaults: {
            token: '',
            username: '',
            tipo: '',
            fechaVigencia: (new Date()).getTime()
        },

        initialize: function() {
        },

        validation: {
            token: {
                required: true
            },
            username: {
                required: true,
                pattern: 'email'
            },
            tipo: {
                required: true
            },
            fechaVigencia: {
                required: true
            }
        }

    });

    return UserTokenModel;
});
