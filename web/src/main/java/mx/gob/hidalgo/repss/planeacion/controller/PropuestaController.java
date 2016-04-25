package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import mx.gob.hidalgo.repss.planeacion.services.PropuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
@Controller
@RequestMapping("/propuesta")
public class PropuestaController {
    @Autowired
    PropuestaService propuestaService;

    /* Metodo que guarda una nueva propuesta*/
    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createPropuesta(@RequestBody Map<String, String> propuesta) {
        return propuestaService.createPropuesta(propuesta);
    }
    /* Metodo que regresa la lista de municipios que continen propuestas, recibe de parametro el id del municipio mediante la url*/
    @ResponseBody
    @RequestMapping(
            value = { "/propuesta/byMunicipio/{municipio}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> listPropuestasByMunicipio(@PathVariable("municipio") Long idMunicipio) {
        return propuestaService.listPropuestasByMunicipio(idMunicipio);
    }
    /* Metodo que regresa la lista de localidades que continen propuestas, recibe de parametro el id de la localidad mediante la url*/
    @ResponseBody
    @RequestMapping(
            value = { "/propuesta/byLocalidad/{localidad}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> listPropuestaByLocalidades(@PathVariable("localidad") Long idLocalidad) {
        return propuestaService.listPropuestaByLocalidades(idLocalidad);
    }
}
