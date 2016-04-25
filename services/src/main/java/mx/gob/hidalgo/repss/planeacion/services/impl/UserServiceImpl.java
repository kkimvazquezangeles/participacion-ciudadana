package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.TipoToken;
import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.model.UserRole;
import mx.gob.hidalgo.repss.planeacion.model.UserToken;
import mx.gob.hidalgo.repss.planeacion.repositories.UserRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.UserRoleRepository;
import mx.gob.hidalgo.repss.planeacion.services.MailService;
import mx.gob.hidalgo.repss.planeacion.services.PersonaService;
import mx.gob.hidalgo.repss.planeacion.services.UserService;
import mx.gob.hidalgo.repss.planeacion.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kkimvazquezangeles on 07/04/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonaService personaService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    MailService mailService;

    @Autowired
    SimpleMailMessage templateMessage;

    @Override
    public Map<String, Object> findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Map<String, Object> result = convertUserToMap(user);
        result.putAll(personaService.listJugadorByAdmin(user));
        return result;
    }

    @Override
    public Map<String, Object> createUser(Map<String, String> userMap) {
        User user = new User();
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.FALSE));
            user = userRepository.save(convertMapToUser(userMap));
            TipoToken tipoToken = (get(user.getUsername())==null) ? TipoToken.VALID_EMAIL : TipoToken.CHANGE_PASSWORD;
            userRoleRepository.save(generateRoleDefaultByUser(user));
            UserToken userToken = userTokenService.createUserTokenByUserAndTipo(user, tipoToken);
            sendMailToken(userToken, userMap.get(PROPERTY_CONTEXT));
        }
        return convertUserToMap(user);
    }

    @Override
    public Map<String, Object> changePassword(Map<String, String> userMap) {
        User user = new User();
        Map<String, Object> userTokenMap = userTokenService.userTokenByIdAndTipo(
                userMap.get(UserTokenService.PROPERTY_TOKEN), TipoToken.CHANGE_PASSWORD);
        userMap.put(PROPERTY_USERNAME, (String) userTokenMap.get(PROPERTY_USERNAME));
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.TRUE));
            user = userRepository.save(convertMapToUser(userMap));
        }
        return convertUserToMap(user);
    }

    private Map<String, Object> convertUserToMap(User user){
        Map<String, Object> sessionDTO = new HashMap<String, Object>();
        sessionDTO.put(PROPERTY_ROLES, getRolesByUser(user));
        sessionDTO.put(PROPERTY_ID, String.valueOf(user.getUsername()));
        sessionDTO.put(PROPERTY_USERNAME, user.getUsername());
        return sessionDTO;
    }

    private User convertMapToUser(Map<String, String> userMap) {
        User user = new User();
        user.setUsername(userMap.get(PROPERTY_USERNAME));
        user.setPassword(userMap.get(PROPERTY_PASSWORD));
        user.setEnabled(Boolean.parseBoolean(userMap.get(PROPERTY_ENABLED)));
        return user;
    }

    private List<String> getRolesByUser(User user){
        user.setUserRole(userRoleRepository.findAllByUser(user));
        Iterator<UserRole> iter = user.getUserRole().iterator();
        List<String> roles = new ArrayList<String>();
        while (iter.hasNext()) {
            UserRole userRole = iter.next();
            roles.add(userRole.getRole());
        }
        return roles;
    }

    private UserRole generateRoleDefaultByUser(User user) {
        UserRole role = new UserRole();
        role.setUser(user);
        role.setRole(PROPERTY_ROLE_DEFAULT);
        return role;
    }

    private User get(String username){
        return userRepository.findOne(username);
    }

    private void sendMailToken(UserToken userToken, String context) {
        templateMessage.setTo(userToken.getUser().getUsername());
        templateMessage.setCc(userToken.getUser().getUsername());

        Map<String, Object> props = new HashMap<>();
        props.put("action", userToken.getTipo().getDescription());
        props.put("link", context + "#token/" + userToken.getToken());

        mailService.send(templateMessage, props);
    }
}
