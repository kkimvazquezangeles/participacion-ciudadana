package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.services.MailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Map;

/**
 * Created by kkimvazquezangeles on 15/02/16.
 */
@Service
public class VelocityMailServiceImpl implements MailService {

    @Autowired
    VelocityEngine velocityEngine;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void send(final SimpleMailMessage msg, final Map<String, Object> hTemplateVariables) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(msg.getTo());
            message.setCc(msg.getCc());
            message.setFrom(msg.getFrom());
            message.setSubject(msg.getSubject());

            String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailBody.vm", "UTF-8", hTemplateVariables);

            message.setText(body, true);
        };

        mailSender.send(preparator);
    }
}