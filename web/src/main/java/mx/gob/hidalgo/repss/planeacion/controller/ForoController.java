package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.services.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 17/05/16.
 */
@Controller
@RequestMapping("/foro")
public class ForoController {

    @Autowired
    ForoService foroService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listForos() {
        return foroService.listForos();
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{foro}/temas" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listLocalidades(@PathVariable("foro") Long idForo) {
        return foroService.listTemasByForo(idForo);
    }
}
