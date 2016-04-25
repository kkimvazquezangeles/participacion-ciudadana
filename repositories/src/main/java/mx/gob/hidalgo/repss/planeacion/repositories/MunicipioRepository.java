package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

}
