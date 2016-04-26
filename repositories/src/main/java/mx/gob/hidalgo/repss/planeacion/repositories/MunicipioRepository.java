package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {
    @Query("select distinct pt.persona.localidad.municipio from Propuesta pt")
    List<Municipio> findAllAtLeastPropuesta();
}
