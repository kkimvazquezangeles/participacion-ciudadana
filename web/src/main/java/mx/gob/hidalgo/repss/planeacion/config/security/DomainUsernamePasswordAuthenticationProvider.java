package mx.gob.hidalgo.repss.planeacion.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    public DomainUsernamePasswordAuthenticationProvider(){

    }

    public DomainUsernamePasswordAuthenticationProvider(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if (username.isEmpty() || password.isEmpty()) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

        UserDetails user = userDetailsService.loadUserByUsername(String.valueOf(username));

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException("Unvalidated user, check your mail.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        AuthenticationWithToken authenticationWithToken = new AuthenticationWithToken(username, password, authorities);


        String newToken = tokenService.generateNewToken();
        authenticationWithToken.setToken(newToken);
        tokenService.store(newToken, authenticationWithToken);

        return authenticationWithToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
