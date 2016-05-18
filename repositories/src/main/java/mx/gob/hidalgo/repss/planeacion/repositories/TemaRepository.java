package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Foro;
import mx.gob.hidalgo.repss.planeacion.model.Tema;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
public interface TemaRepository extends CrudRepository<Tema, Long> {
    List<Tema> findAllByForo(Foro foro);
}
