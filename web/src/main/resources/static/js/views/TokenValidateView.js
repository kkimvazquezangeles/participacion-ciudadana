define([
    'jquery',
    'core/BaseView',
    'models/UserTokenModel',
    'views/TokenChangePassView',
    'text!templates/tplTokenValidate.html',
    'text!templates/tplTokenInValid.html'
], function($, BaseView, UserTokenModel, TokenChangePassView, tplTokenValidate, tplTokenInValid){

    var TokenValidateView = BaseView.extend({
        template: _.template(tplTokenValidate),
        templateInValid: _.template(tplTokenInValid),

        events: {
            'click #btn-ok'         : 'changePass'
        },

        initialize: function(opts) {;
            this.model = new UserTokenModel();
            this.listenTo(this.model, 'sync', this.syncUserToken);
            this.listenTo(this.model, 'error', this.errorUserToken);

            this.model.set({id: opts.token});
            this.model.fetch();
            Backbone.Validation.bind(this);
        },

        render: function() {
            return this;
        },

        syncUserToken: function() {
            if (this.model.get('tipo') === 'VALID_EMAIL') {
                this.$el.html(this.template(this.model.toJSON()));
            } else {
                this.$el.html(this.templateInValid({message: 'Token no valido.'}));
            }
        },

        errorUserToken: function(model, response) {
            this.$el.html(this.templateInValid(response.responseJSON));
        }
    });

    return TokenValidateView;

});