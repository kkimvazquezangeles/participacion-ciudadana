define([
	'backbone',
    'models/TemaModel'
], function(Backbone, TemaModel){

    var TemasCollection = Backbone.Collection.extend({
        model: TemaModel,
        url: function() {
            return 'foro/' + this.idForo + '/temas' ;
        },
        setIdForo: function(idForo){
            this.idForo = idForo;
        }
    });

	return TemasCollection;
});