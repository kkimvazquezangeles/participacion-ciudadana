package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.model.Departamento;
import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.services.ArchivoService;
import mx.gob.hidalgo.repss.planeacion.services.GeneralService;
import mx.gob.hidalgo.repss.planeacion.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
@Controller
@RequestMapping("/archivo")
public class ArchivoController {

    @Autowired
    ArchivoService archivoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createArchivo(@RequestBody Map<String, String> archivo, User user) {
        return archivoService.createArchivo(archivo, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{archivo}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateArchivo(@RequestBody Map<String, String> archivo, User user) {
        return archivoService.createArchivo(archivo, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/depto/{depto}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listJugadorByUser(@PathVariable("depto") Long idDepartamento) {
        Departamento depto = new Departamento();
        depto.setId(idDepartamento);
        return archivoService.listArchivoByDepartamento(depto);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{archivo}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteArchivo(@PathVariable("archivo") Long idArchivo) {
        Map<String, Object> response = new HashMap<>();
        response.put(PersonaService.PROPERTY_ID, idArchivo);
        archivoService.deleteArchivo(idArchivo);
        response.put(GeneralService.PROPERTY_RESULT, true);
        response.put(GeneralService.PROPERTY_MESSAGE, "Archivo eliminado");
        return response;


    }
}
