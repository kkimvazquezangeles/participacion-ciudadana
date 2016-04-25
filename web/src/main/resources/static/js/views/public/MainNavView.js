define([
	'jquery',
	'core/BaseView',
	'text!templates/public/tplMainNav.html',
	'Session'
], function($, BaseView, tplMainNav, Session){

	var MainNavView = BaseView.extend({
	    el: $("#hotel-nav"),

        template: _.template(tplMainNav),

        events: {
            'click #signin'         : 'signin',
            'click #signup'         : 'signup',
            'click #home'           : 'home'
        },

        initialize: function() {
            this.render();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        signin: function(){
            Backbone.history.navigate('admin', { trigger : true });
        },

        signup: function(){
            Backbone.history.navigate('signup', { trigger : true });
        },

        home: function(){
            Backbone.history.navigate('', { trigger : true });
        }
	});

	return MainNavView;

});