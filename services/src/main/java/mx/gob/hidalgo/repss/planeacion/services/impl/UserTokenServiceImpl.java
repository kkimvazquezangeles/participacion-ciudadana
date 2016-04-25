package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.exception.TokenException;
import mx.gob.hidalgo.repss.planeacion.model.TipoToken;
import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.model.UserToken;
import mx.gob.hidalgo.repss.planeacion.repositories.UserRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.UserTokenRepository;
import mx.gob.hidalgo.repss.planeacion.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kkimvazquezangeles on 18/02/16.
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    UserTokenRepository userTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<String, Object> userTokenByIdAndTipo(String token, TipoToken tipoToken) {
        UserToken userToken = userTokenRepository.findOne(token);
        if (userToken == null) {
            throw new TokenException("Token no existe.");
        }
        if (userToken.getFechaVigencia().compareTo(new Date()) < 0) {
            throw new TokenException("Token expirado.");
        }
        if (!userToken.getTipo().equals(tipoToken)) {
            throw new TokenException("Tipo Token invalido.");
        }
        userToken.getUser().setEnabled(true);
        userRepository.save(userToken.getUser());
        userTokenRepository.delete(userToken.getToken());
        return convertUserTokenToMap(userToken);
    }

    @Override
    public UserToken createUserTokenByUserAndTipo(User user, TipoToken tipo) {
        return userTokenRepository.save(generateTokenByUserAndTipo(user, tipo));
    }

    private Map<String, Object> convertUserTokenToMap(UserToken userToken){
        Map<String, Object> sessionDTO = new HashMap<String, Object>();
        sessionDTO.put(PROPERTY_TOKEN, userToken.getToken());
        sessionDTO.put(PROPERTY_TIPO, userToken.getTipo());
        sessionDTO.put(PROPERTY_FECHA_VIGENCIA, userToken.getFechaVigencia());
        sessionDTO.put(PROPERTY_USERNAME, userToken.getUser().getUsername());
        return sessionDTO;
    }

    private UserToken generateTokenByUserAndTipo(User user, TipoToken tipo) {
        UserToken token = new UserToken();
        Calendar fechaVigencia = Calendar.getInstance();
        fechaVigencia.add(Calendar.DAY_OF_MONTH, PROPERTY_TOKEN_VIGENCIA_DAYS);
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setTipo(tipo);
        token.setFechaVigencia(fechaVigencia.getTime());
        return token;
    }

}
