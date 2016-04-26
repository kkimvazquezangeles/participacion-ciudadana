package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface LocalidadRepository extends CrudRepository<Localidad, Long> {
    List<Localidad> findAllByMunicipio(Municipio municipio);
    @Query("select distinct pt.persona.localidad from Propuesta pt where pt.persona.localidad.municipio = :municipio")
    List<Localidad> findAllByMunicipioAtLeastPropuesta( @Param("municipio")Municipio municipio);
}
