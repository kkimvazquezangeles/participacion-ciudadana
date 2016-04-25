package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.config.TestConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 17/02/16.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Ignore
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    SimpleMailMessage templateMessage;

    @Test
    public void testSend() {
        templateMessage.setTo("rolguin@grupobmv.com.mx");
        templateMessage.setCc("rolguin@grupobmv.com.mx");

        Map<String, Object> props = new HashMap<>();
        props.put("action", "Registrar");
        props.put("link", "lcalhosto:8090/#token/SDFSADF34FHF435YT67KJ45");
        mailService.send(templateMessage, props);
    }
}
