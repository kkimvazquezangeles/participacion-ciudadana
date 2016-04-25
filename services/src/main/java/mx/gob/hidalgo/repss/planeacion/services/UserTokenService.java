package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.model.TipoToken;
import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.model.UserToken;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 18/02/16.
 */
public interface UserTokenService {
    int PROPERTY_TOKEN_VIGENCIA_DAYS    = 2;

    String PROPERTY_TOKEN               = "token";
    String PROPERTY_TIPO                = "tipo";
    String PROPERTY_FECHA_VIGENCIA      = "fechaVigencia";
    String PROPERTY_USERNAME            = "username";

    Map<String,Object> userTokenByIdAndTipo(String token, TipoToken tipoToken);

    UserToken createUserTokenByUserAndTipo(User user, TipoToken tipo);
}
