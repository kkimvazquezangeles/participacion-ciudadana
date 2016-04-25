package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.model.Departamento;
import mx.gob.hidalgo.repss.planeacion.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 15/03/16.
 */
public interface ArchivoService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_NOMBRE              = "nombre";
    String PROPERTY_RUTA_NOMBRE         = "rutaNombre";
    String PROPERTY_DEPTO_ID            = "deptoId";
    String PROPERTY_DEPTO_DES           = "deptoDes";
    String PROPERTY_USER_ID             = "userId";
    String PROPERTY_USER_DES            = "userDes";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";

    Map<String,Object> createArchivo(Map<String, String> archivo, User user);

    void deleteArchivo(Long idArchivo);

    List<Map<String,Object>> listArchivoByDepartamento(Departamento departamento);
}
