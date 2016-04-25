package mx.gob.hidalgo.repss.planeacion.services;

import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface MunicipioService {

    String PROPERTY_MUNICIPIO_ID = "idMunicipio";
    String PROPERTY_LOCALIDAD_ID = "idLocalidad";
    String PROPERTY_MUNICIPIO = "municipio";
    String PROPERTY_LOCALIDAD = "localidad";

    List<Map<String,Object>> listMunicipios();
    List<Map<String,Object>> listMunicipiosPropuesta();
    List<Map<String,Object>> listLocalidadesByMunicipio(Long idMunicipio);


}
