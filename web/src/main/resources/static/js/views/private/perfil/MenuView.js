define([
    'jquery',
    'backbone',
    'core/BaseView',
    'text!templates/private/perfil/tplMenu.html',
    'Session'
], function($, Backbone, BaseView, tplMenu, Session){

    var MenuView = BaseView.extend({
        template: _.template(tplMenu),

        events: {
            'click .menu-action' : 'opcionMenu'
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        opcionMenu: function(event) {
        }
    });

    return MenuView;

});