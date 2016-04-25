define([
	'backbone',
    'models/LocalidadModel'
], function(Backbone, LocalidadModel){

    var LocalidadesCollection = Backbone.Collection.extend({
        model: LocalidadModel,
        url: function() {
            return 'municipio/' + this.idMunicipio + '/localidades' ;
        },
        setIdMunicipio: function(idMunicipio){
            this.idMunicipio = idMunicipio;
        }
    });

	return LocalidadesCollection;
});