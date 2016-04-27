define([
    'jquery',
    'backbone',
    'karto',
    'raphael',
    'core/BaseView',
    'models/PerfilModel',
    'models/FileModel',
    'models/MunicipioModel',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/MunicipiosPropuestaCollection',
    'collections/LocalidadesPropuestaCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html',
    'Session'
], function($, Backbone, karto, raphael, BaseView, PerfilModel, FileModel, MunicipioModel,
            ModalGenericView, FilesView, MunicipiosPropuestaCollection, LocalidadesPropuestaCollection,
            tplPerfilAdmin, Session){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-exit'           : 'logout',
            'change #select-municipios' : 'cambiarMunicipio'
        },

        initialize: function() {
            this.model = new MunicipioModel();
            Backbone.Validation.bind(this);

            this.municipios = new MunicipiosPropuestaCollection();
            this.listenTo(this.municipios, 'add', this.agregarMunicipio);
            this.listenTo(this.municipios, 'sync', this.syncMunicipio);
            this.localidades = new LocalidadesPropuestaCollection();
            this.listenTo(this.localidades, 'add', this.agregarLocalidad);
            this.listenTo(this.localidades, 'sync', this.syncLocalidad);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.municipios.fetch();
            return this;
        },

        logout: function(){
            Session.logout(function(response){
                Backbone.history.navigate('', { trigger : true });
            });
        },

        agregarMunicipio: function(modelo){
            $('#select-municipios').append($('<option>', {
                value: modelo.get('idMunicipio'),
                text : modelo.get('municipio')
            }));
        },

        syncMunicipio: function(){
            $('#select-municipios').change();
            this.setUpMap();
        },

        agregarLocalidad: function(modelo){
            $('#select-localidades').append($('<option>', {
                value: modelo.get('idLocalidad'),
                text : modelo.get('localidad')
            }));
        },

        syncLocalidad: function(){
        },

        cambiarMunicipio: function(event) {
            var idMunicipio = $(event.target).val();
            this.localidades.setIdMunicipio(idMunicipio);
            $('#select-localidades').html('');
            $('#select-localidades').append($('<option>', {
                value: ('0'),
                text : ('Localidad')
            }));
            this.localidades.fetch();

        },

        setUpMap: function(){
	        var svgUrl = 'imagenes/HG.svg',
    	        opts = { padding: 0 };

            kartograph.map('#map').loadMap(svgUrl, this.mapLoaded, opts);
        },

        mapLoaded: function(map){
            map.addLayer('admin1', {
                styles: {
                    stroke: '#aaa',
                    fill: '#f6f4f2'
                },
                mouseenter: function(d, path) {
                    path.attr('fill', Math.random() < 0.5 ? '#c04' : '#04c');
                },
                mouseleave: function(d, path) {
                    path.animate({ fill: '#f6f4f2' }, 1000);
                }
            });

            var points_of_interest = [

            ];

            map.addSymbols({
                type: kartograph.LabeledBubble,
                data: points_of_interest,
                location: function(d) { return [d.lon, d.lat] },
                title: function(d) { return d.name; },
                radius: 3,
                center: false,
                attrs: { fill: 'black' },
                labelattrs: { 'font-size': 11 },
                buffer: true
            });
        }
    });

    return PerfilAdminView;

});