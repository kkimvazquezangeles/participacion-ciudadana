package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Persona;
import mx.gob.hidalgo.repss.planeacion.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
public interface PersonaRepository extends CrudRepository<Persona, Long> {
    Persona findByAdmin(User admin);
}
