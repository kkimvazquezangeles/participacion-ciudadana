package mx.gob.hidalgo.repss.planeacion.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BackendAdminUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public BackendAdminUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
