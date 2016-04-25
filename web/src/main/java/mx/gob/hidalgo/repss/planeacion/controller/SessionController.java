package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.config.security.AuthenticationWithToken;
import mx.gob.hidalgo.repss.planeacion.config.security.TokenService;
import mx.gob.hidalgo.repss.planeacion.services.UserService;
import mx.gob.hidalgo.repss.planeacion.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 07/04/15.
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationProvider domainUsernamePasswordAuthenticationProvider;

    @Autowired
    TokenService tokenService;

    @ResponseBody
    @RequestMapping(
            value = { "/login" },
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> login(
            @RequestBody Map<String, String> user, HttpServletRequest httpRequest) {

        if (((!user.containsKey("username") || !user.containsKey("password"))
                && !user.containsKey("logout"))){
            throw new BadCredentialsException("Username or password not found.");
        }

        if (user.containsKey("logout") && httpRequest.getHeader("X-Auth-Token") == null){
            throw new BadCredentialsException("token not found.");
        }

        Map<String, Object> sessionDTO = new HashMap<String, Object>();
        sessionDTO = userService.findByUsername(user.get("username"));

        if (user.containsKey("logout")) {
            if (sessionDTO != null){
                tokenService.delete(httpRequest.getHeader("X-Auth-Token"));
            }
            return new HashMap<String, Object>();
        } else {
            Authentication auth = new AuthenticationWithToken(user.get("username"), user.get("password"), null);;
            Authentication authToken = domainUsernamePasswordAuthenticationProvider.authenticate(auth);

            sessionDTO.put(UserTokenService.PROPERTY_TOKEN, authToken.getDetails());
        }
        return sessionDTO;
    }

}
