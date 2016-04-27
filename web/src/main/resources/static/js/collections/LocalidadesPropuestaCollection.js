define([
	'backbone',
    'models/MunicipioModel'
], function(Backbone, MunicipioModel){

    var LocalidadesPropuestaCollection = Backbone.Collection.extend({
        model: MunicipioModel,
        url: function() {
            return 'municipio/' + this.idMunicipio + '/localidades/propuesta';
        },
        setIdMunicipio: function(idMunicipio){
            this.idMunicipio = idMunicipio;
        }
    });

	return LocalidadesPropuestaCollection;
});


