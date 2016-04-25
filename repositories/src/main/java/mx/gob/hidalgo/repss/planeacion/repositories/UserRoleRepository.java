package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.UserRole;
import mx.gob.hidalgo.repss.planeacion.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by kkimvazquezangeles on 27/01/15.
 */
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    Set<UserRole> findAllByUser(User user);
}