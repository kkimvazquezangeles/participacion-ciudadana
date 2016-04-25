package mx.gob.hidalgo.repss.planeacion.controller;


import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import mx.gob.hidalgo.repss.planeacion.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
@Controller
@RequestMapping("/municipio")
public class MunicipioController {
    @Autowired
    MunicipioService municipioService;

    /* Metodo que regresa la lista de municipios, no recibe parametros */
    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listMunicipios() {
        return municipioService.listMunicipios();
    }

    /* Metodo que regresa la lista de municipios que continen propuestas, no recibe parametros */
    @ResponseBody
    @RequestMapping(
            value = { "/propuesta" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listMunicipiosPropuesta() {
        return municipioService.listMunicipiosPropuesta();
    }


    /* Metodo que regresa la lista de localidades por municipio, recibe de parametro el id del municipio mediante la url*/
    @ResponseBody
    @RequestMapping(
            value = { "/{municipio}/localidades" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listLocalidades(@PathVariable("municipio") Long idMunicipio) {
        return municipioService.listLocalidadesByMunicipio(idMunicipio);
    }
}
