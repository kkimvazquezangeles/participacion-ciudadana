package mx.gob.hidalgo.repss.planeacion.infrastructure;

import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.repositories.UserRepository;
import mx.gob.hidalgo.repss.planeacion.services.GeneralService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
@Aspect
@Component
public class UserSessionAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletResponse response;

    @Pointcut("execution(* mx.gob.hidalgo.repss.planeacion.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @Pointcut("execution(* mx.gob.hidalgo.repss.planeacion.controller.*Controller.delete*(..))")
    public void controllerLayerDeleteMethod() {
    }

    @Around("controllerLayer()")
    public Object aroundControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        logger.info("Argumento " + username);

        for(Object arg :pjp.getArgs()){
            if(arg instanceof User && username != null && !username.isEmpty()) {
                User user = userRepository.findOne(username);
                if (user != null) {
                    ((User) arg).setUsername(user.getUsername());
                    ((User) arg).setEnabled(user.isEnabled());
                    ((User) arg).setUserRole(user.getUserRole());
                }
            }
        }

        Object retVal = pjp.proceed();
        return retVal;
    }

    @AfterReturning(
            pointcut = "controllerLayerDeleteMethod())",
            returning= "result")
    public void deleteAfterReturning(JoinPoint joinPoint, Object result) {
        Map<String, Object> resultDelete = (Map<String, Object>) result;
        if (!(boolean)resultDelete.get(GeneralService.PROPERTY_RESULT)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
