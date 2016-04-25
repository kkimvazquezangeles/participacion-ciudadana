package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.UserToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kkimvazquezangeles on 10/02/16.
 */
public interface UserTokenRepository extends CrudRepository<UserToken, String> {
}
