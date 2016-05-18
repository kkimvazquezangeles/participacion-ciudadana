define([
	'backbone',
    'models/ForoModel'
], function(Backbone, ForoModel){

    var ForosCollection = Backbone.Collection.extend({
        model: ForoModel,
        url: function() {
            return 'foro' ;
        }
    });

	return ForosCollection;
});