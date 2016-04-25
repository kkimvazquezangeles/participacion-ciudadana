package mx.gob.hidalgo.repss.planeacion.config.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "mx.gob.hidalgo.repss.planeacion.infrastructure" })
public class InfrastructureConfig {


}
