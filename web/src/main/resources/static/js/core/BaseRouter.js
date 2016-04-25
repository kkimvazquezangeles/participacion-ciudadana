define([
	'underscore',
	'backbone'
], function(_, Backbone){

	var BaseRouter = Backbone.Router.extend({
		before: function(router, args){	},
		after: function(router, args){	},
		route : function (route, name, callback) {
			if (!_.isRegExp(route)) route = this._routeToRegExp(route);
			if (_.isFunction(name)) {
				callback = name;
				name = '';
			}
			if (!callback) callback = this[name];

			var router = this;

			Backbone.history.route(route, function(fragment) {
				/*var args = router._extractParameters(route, fragment);

				router.before.apply(router, args);
				callback && callback.apply(router, args);
				router.after.apply(router, args);

				router.trigger.apply(router, ['route:' + name].concat(args));
				router.trigger('route', name, args);
				Backbone.history.trigger('route', router, name, args);*/

				var args = router._extractParameters(route, fragment);
				var next = function(){
					callback && callback.apply(router, args);
					router.trigger.apply(router, ['route:' + name].concat(args));
					router.trigger('route', name, args);
					Backbone.history.trigger('route', router, name, args);
					router.after.apply(router, args);
				}
				router.before.apply(router, [args, next]);
			});
			return this;
		}

	});

	return BaseRouter;
});