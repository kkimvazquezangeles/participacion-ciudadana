define([
	'backbone'
], function(Backbone){

    var FileModel = Backbone.Model.extend({

        urlRoot: 'archivo',

        defaults: {
            nameFile: '',
            dateFile: ''
        }

    });

	return FileModel;
});