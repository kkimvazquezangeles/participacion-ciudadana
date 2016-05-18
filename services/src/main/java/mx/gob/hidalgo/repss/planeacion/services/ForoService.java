package mx.gob.hidalgo.repss.planeacion.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 17/05/16.
 */
public interface ForoService {
    String PROPERTY_FORO_ID = "idForo";
    String PROPERTY_TEMA_ID = "idTema";
    String PROPERTY_FORO = "foro";
    String PROPERTY_TEMA = "tema";

    List<Map<String,Object>> listForos();
    List<Map<String,Object>> listTemasByForo(Long idForo);

}
