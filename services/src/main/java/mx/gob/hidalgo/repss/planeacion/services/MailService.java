package mx.gob.hidalgo.repss.planeacion.services;

import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 25/02/16.
 */
public interface MailService {
    void send(SimpleMailMessage msg, Map<String, Object> hTemplateVariables);
}
