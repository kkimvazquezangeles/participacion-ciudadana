define([
	'backbone',
    'models/MunicipioModel'
], function(Backbone, MunicipioModel){

    var MunicipiosCollection = Backbone.Collection.extend({
        model: MunicipioModel,
        url: function() {
            return 'municipio';
        }
    });

	return MunicipiosCollection;
});