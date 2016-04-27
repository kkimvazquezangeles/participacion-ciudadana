define([
	'backbone',
    'models/util/MapModel'
], function(Backbone, MapModel){

    var MapCollection = Backbone.Collection.extend({
        model: MapModel,
        url: function() {
            return 'propuesta/totalByMunicipio';
        }
    });

	return MapCollection;
});