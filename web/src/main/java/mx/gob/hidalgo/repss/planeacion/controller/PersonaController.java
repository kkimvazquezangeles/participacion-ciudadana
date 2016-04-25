package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.services.GeneralService;
import mx.gob.hidalgo.repss.planeacion.services.PersonaService;
import mx.gob.hidalgo.repss.planeacion.services.UserService;
import mx.gob.hidalgo.repss.planeacion.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createPersona(@RequestBody Map<String, String> jugador, User user) {
        return personaService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jugador}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updatePersona(@RequestBody Map<String, String> jugador, User user) {
        return personaService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = "/{username:.+}",
            method = {RequestMethod.GET},
            headers="Accept=*/*",
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> findPersonalByUser(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> listPersonaByUser(User user) {
        return personaService.listJugadorByAdmin(user);
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{jugador}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteLiga(@PathVariable("jugador") Long idJugador, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(PersonaService.PROPERTY_ID, idJugador);
        DeleteStatusEnum result = personaService.deleteJugador(idJugador);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Persona eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El jugador no se puede eliminar, ya participa en torneo(s)");
        }
        return response;
    }
}
