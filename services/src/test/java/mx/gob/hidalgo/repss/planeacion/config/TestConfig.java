package mx.gob.hidalgo.repss.planeacion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by kkimvazquezangeles on 16/02/16.
 */
@Configuration
@Import({ PersistenceConfig.class, ServicesConfig.class, MailConfig.class})
public class TestConfig {
}
