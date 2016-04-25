define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/UserModel',
    'views/private/util/ModalGenericView',
	'text!templates/tplSignup.html'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, UserModel, ModalGenericView, tplSignup){

	var SignupView = BaseView.extend({
        template: _.template(tplSignup),

        events: {
            'click #btn-ok'         : 'signup'
        },

        initialize: function() {
            this.model = new UserModel();
            this.model.once("sync", this.saveUserSuccess);
            this.model.once("error", this.saveUserError);
            Backbone.Validation.bind(this);
            app.that = this;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        signup: function(){
            var data = this.$el.find("#form-user").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveUserSuccess: function(model, response, options){
            if (typeof app.that === 'undefined') {
                return;
            }
            new ModalGenericView({
                message: 'Revise su email, para confirmar su registro'
            });
            app.that.destroyView();
            delete app.that;
        },

        saveUserError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el usuario'
            });
        }

	});

	return SignupView;

});