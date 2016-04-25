define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'text!templates/private/tplMainAdmin.html'
], function($, bootstrap, BaseView, tplMainAdmin){

	var MainAdminView = BaseView.extend({
        template: _.template(tplMainAdmin),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            $('nav').show();
            return this;
        }
	});

	return MainAdminView;

});