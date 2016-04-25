define([
    'jquery',
    'core/BaseView',
    'models/UserModel',
    'views/private/util/ModalGenericView',
    'text!templates/tplTokenChangePass.html'
], function($, BaseView, UserModel, ModalGenericView, tplTokenChangePass){

    var TokenChangePassView = BaseView.extend({
        template: _.template(tplTokenChangePass),

        events: {
            'click #btn-ok'         : 'changePass'
        },

        initialize: function(opts) {;
            this.model = new UserModel();
            this.listenTo(this.model, 'sync', this.syncUserToken);
            this.listenTo(this.model, 'error', this.errorUserToken);

            this.model.set({id: 'no-replay@lupa.com', username: 'no-replay@lupa.com', token: opts.token});
            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        changePass: function(){
            var data = this.$el.find("#form-user").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        syncUserToken: function() {
            new ModalGenericView({
                message: 'Su password se ha restaurado',
                callbackConfirm: function (data) {
                    Backbone.history.navigate('login', { trigger : true });
                }
            });
        },

        errorUserToken: function(model, response) {
            new ModalGenericView({
                message: response.responseJSON.message
            });
        }
    });

    return TokenChangePassView;

});