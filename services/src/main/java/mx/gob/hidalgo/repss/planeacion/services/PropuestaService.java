package mx.gob.hidalgo.repss.planeacion.services;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface PropuestaService {


    String PROPERTY_PERSONA_ID      = "idPersona";
    String PROPERTY_NOMBRE          = "nombre";
    String PROPERTY_PATERNO         = "paterno";
    String PROPERTY_MATERNO         = "materno";
    String PROPERTY_CORREO          = "correo";
    String PROPERTY_TELEFONO        = "telefono";
    String PROPERTY_ID_MUNICIPIO    = "idMunicipio";
    String PROPERTY_ID_LOCALIDAD    = "idLocalidad";
    String PROPERTY_ID              = "id";
    String PROPERTY_PROPUESTA1      = "propuesta1";
    String PROPERTY_PROPUESTA2      = "propuesta2";
    String PROPERTY_PROPUESTA3      = "propuesta3";
    String PROPERTY_PROPUESTA4      = "propuesta4";

    Map<String,Object> createPropuesta(Map<String, String> propuesta);
    Map<String,Object> listPropuestasByMunicipio(Long idMunicipio);
    Map<String,Object> listPropuestaByLocalidades(Long idLocalidad);

}
