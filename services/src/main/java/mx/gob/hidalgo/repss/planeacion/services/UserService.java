package mx.gob.hidalgo.repss.planeacion.services;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 07/04/15.
 */
public interface UserService {
    String PROPERTY_ID                  = "id";
    String PROPERTY_USERNAME            = "username";
    String PROPERTY_PASSWORD            = "password";
    String PROPERTY_PASSWORD_CONFIRM    = "passwordConfirm";
    String PROPERTY_ENABLED             = "enabled";
    String PROPERTY_ROLES               = "roles";
    String PROPERTY_CONTEXT             = "context";

    String PROPERTY_ROLE_DEFAULT        = "PUBLIC";

    Map<String, Object> findByUsername(String username);

    Map<String,Object> createUser(Map<String, String> user);

    Map<String,Object> changePassword(Map<String, String> user);
}
