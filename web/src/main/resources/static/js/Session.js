define([
	'jquery',
	'backbone',
    'jquerycookie',
	'views/private/util/ModalGenericView'
], function($, Backbone, jquerycookie, ModalGenericView){

	var SessionModel = Backbone.Model.extend({
	    url : 'session/login',

        defaults: {
            username: '',
            password: ''
        },

	    initialize: function(){
	    	this.set('authenticated', false);
	    },

		login : function(callback, user, pass, remember){
			var that = callback;
			var Session = this;

			this.save({
						username: user,
						password: pass}, {
				wait:true,
				success:function(model, response) {
					Session.set('authenticated', true);
					Session.set('username', user);
					Session.set('roles', model.get('roles'));
					if (remember) {
						$.cookie('auth_token',
						    JSON.stringify({
						        username: user,
						        token: model.get('token'),
						        roles: model.get('roles')
						    }));
					}

					$.ajaxSetup({
						headers: {
							"X-Auth-Token": model.get('token')
						}
					});
					console.log('Successfully saved!');
					that();
				},
				error: function(model, error) {
					Session.set('authenticated', false);
					Session.set('username', '');
					Session.set('roles', []);
                    $.removeCookie('auth_token')
                    $('#msg-error').show();
				}
			});
		},

		logout : function(callback){
			var thisSession = this;
			var that = callback;
			var Session = new SessionModel();

			Session.save({ username: this.get('username'),
						   logout: 'logout'}, {
				wait:true,
				success:function(model, response) {
					thisSession.clear();
					$.ajaxSetup({
						headers: {
							"X-Auth-Token": ''
						}
					});
                    Session.set('username', '');
                    Session.set('roles', []);
                    $.removeCookie('auth_token')
					console.log('Successfully saved!');
					that();
				},
				error: function(model, error) {
					console.log(model.toJSON());
					console.log('error.responseText');
				}
			});
		}
	});

	return new SessionModel();
});