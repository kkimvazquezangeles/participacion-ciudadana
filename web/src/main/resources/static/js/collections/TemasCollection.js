define([
	'backbone',
    'models/TemaModel'
], function(Backbone, TemaModel){

    var TemasCollection = Backbone.Collection.extend({
        model: TemaaModel,
        url: function() {
            return 'foro/' + this.idForo + '/temas' ;
        },
        setIdForo: function(idForo){
            this.idForo = idForo;
        }
    });

	return TemasCollection;
});