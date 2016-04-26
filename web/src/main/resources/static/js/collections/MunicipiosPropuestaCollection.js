define([
	'backbone',
    'models/MunicipioModel'
], function(Backbone, MunicipioModel){

    var MunicipiosPropuestaCollection = Backbone.Collection.extend({
        model: MunicipioModel,
        url: function() {
            return 'municipio/propuesta'
        }
    });

	return MunicipiosPropuestaCollection;
});