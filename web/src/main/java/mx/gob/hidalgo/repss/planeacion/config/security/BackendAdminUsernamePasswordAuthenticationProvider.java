package mx.gob.hidalgo.repss.planeacion.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

public class BackendAdminUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    public static final String INVALID_BACKEND_ADMIN_CREDENTIALS = "Invalid Backend Admin Credentials";

    @Value("${backend.admin.username}")
    private String backendAdminUsername;

    @Value("${backend.admin.password}")
    private String backendAdminPassword;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if (credentialsMissing(username, password) || credentialsInvalid(username, password)) {
            throw new BadCredentialsException(INVALID_BACKEND_ADMIN_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(username, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_BACKEND_ADMIN"));
    }

    private boolean credentialsMissing(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private boolean credentialsInvalid(String username, String password) {
        return !isBackendAdmin(username) || !password.equals(backendAdminPassword);
    }

    private boolean isBackendAdmin(String username) {
        return backendAdminUsername.equals(username);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BackendAdminUsernamePasswordAuthenticationToken.class);
    }
}
