define([
	'underscore',
	'backbone'
], function(_, Backbone){

	var BaseView = Backbone.View.extend({

		close : function(){
			if(this.childViews){
				this.childViews.close();
			}
			this.remove();
		},

		destroyView: function() {
		    if (this.model !== 'undefined') {
		        this.model.off();
		    }
			// COMPLETELY UNBIND THE VIEW
			this.undelegateEvents();
			this.$el.removeData().unbind();
			// Remove view from DOM
			this.remove();
			Backbone.View.prototype.remove.call(this);
		},

		disabledAction: function(disabled) {
			$('.action-lupa').prop("disabled", disabled);
		}
	});

	return BaseView;

});