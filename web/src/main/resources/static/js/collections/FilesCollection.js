define([
	'backbone',
    'models/FileModel'
], function(Backbone, FileModel){

    var FilesCollection = Backbone.Collection.extend({
        model: FileModel,
        url: function() {
            return 'archivo/depto/' + this.idDepto;
        },
        setIdDepto: function(idDepto){
            this.idDepto = idDepto;
        }
    });

	return FilesCollection;
});