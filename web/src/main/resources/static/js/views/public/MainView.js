define([
	'jquery',
	'bootstrap',
	'bloodhound',
	'typeahead',
	'core/BaseView',
	'text!templates/public/tplMain.html'
], function($, bootstrap, bloodhound, typeahead, BaseView, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        setUp: function() {

        }
	});

	return MainView;

});