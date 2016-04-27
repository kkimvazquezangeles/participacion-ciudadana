package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Persona;
import mx.gob.hidalgo.repss.planeacion.model.Propuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
public interface PropuestaRepository extends CrudRepository<Propuesta, Long> {

    List<Propuesta> findAllByPersona(Persona persona);

    @Query("Select new map(pr.persona.localidad.municipio.id as municipio, count(*) as valor) " +
            "from Propuesta pr " +
            "group by pr.persona.localidad.municipio.id " +
            "order by pr.persona.localidad.municipio.municipio ")
    List<Map> findLimitLideresPuntosByTorneo();

}
