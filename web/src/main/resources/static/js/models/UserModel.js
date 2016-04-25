define([
    'backbone'
], function(Backbone){

    var UserModel = Backbone.Model.extend({

        urlRoot: 'user',

        defaults: {
            username: '',
            password: '',
            passwordConfirm: ''
        },

        initialize: function() {
        },

        validation: {
            username: {
                required: true,
                pattern: 'email',
                msg: 'Por favor especifique un email correcto'
            },
            password: {
                required: true,
                pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/,
                msg: 'El password debe tener por lo menos una letra mayuscula, una minuscula y un numero'
            },
            passwordConfirm: {
                equalTo: 'password',
                msg: 'El password no coincide'
            }
        }

    });

	return UserModel;
});
