define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/util/ModalGenericModel',
	'text!templates/private/util/tplModalGeneric.html'
], function($, Backbone, BaseView, ModalGenericModel, tplModalGeneric){

    var ModelGenericView = BaseView.extend({
        el: '#modal-generic',
        template: _.template(tplModalGeneric),

        events: {
            'click #mg-btn-cancel'     : 'clickCancel',
            'click #mg-btn-confirm'    : 'clickConfirm'
        },

        initialize: function(opts) {
            this.model = new ModalGenericModel();
            this.callbackCancel = opts.callbackCancel;
            this.callbackConfirm = opts.callbackConfirm;
            if (typeof opts.type === 'undefined') {
                opts.type = 'alert';
            }
            if (typeof opts.message === 'undefined') {
                opts.message = ' ';
            }
            if (typeof opts.labelCancel === 'undefined') {
                opts.labelCancel = 'Cancel';
            }
            if (typeof opts.labelConfirm === 'undefined') {
                opts.labelConfirm = 'OK';
            }
            this.model.set({message: opts.message,
                            labelCancel: opts.labelCancel,
                            labelConfirm: opts.labelConfirm});
            this.type = opts.type;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.btnCancel = this.$el.find('#mg-btn-cancel');
            this.btnConfirm = this.$el.find('#mg-btn-confirm');

            if(this.type == 'confirm') {
                this.btnCancel.show();
            } else {
                this.btnCancel.hide();
            }
            this.$('#modal-generic-dialog').modal({backdrop: "static", keyboard: false});
        },

        clickCancel: function(event){
            this.$('#modal-generic-dialog').modal('hide');
            if (typeof this.callbackCancel == 'function') {
                this.callbackCancel(event);
            }
            this.undelegateEvents();
        },

        clickConfirm: function(event) {
            this.$('#modal-generic-dialog').modal('hide');
            if (typeof this.callbackConfirm == 'function') {
                this.callbackConfirm(event);
            }
            this.undelegateEvents();
        }
    });

    return ModelGenericView;
});