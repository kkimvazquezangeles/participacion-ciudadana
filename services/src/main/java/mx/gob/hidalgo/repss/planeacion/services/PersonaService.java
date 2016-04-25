package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.services.impl.DeleteStatusEnum;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
public interface PersonaService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_NOMBRE              = "nombre";
    String PROPERTY_PATERNO             = "paterno";
    String PROPERTY_MATERNO             = "materno";
    String PROPERTY_DEPTO_ID            = "deptoId";
    String PROPERTY_DEPTO_DES           = "deptoDes";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";

    Map<String,Object> createJugador(Map<String, String> jugador, User user);

    DeleteStatusEnum deleteJugador(Long idJugador);

    Map<String,Object> listJugadorByAdmin(User user);
}
