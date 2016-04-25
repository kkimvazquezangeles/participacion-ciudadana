package mx.gob.hidalgo.repss.planeacion;

import mx.gob.hidalgo.repss.planeacion.config.MailConfig;
import mx.gob.hidalgo.repss.planeacion.config.WebConfig;
import mx.gob.hidalgo.repss.planeacion.config.infrastructure.InfrastructureConfig;
import mx.gob.hidalgo.repss.planeacion.config.PersistenceConfig;
import mx.gob.hidalgo.repss.planeacion.config.ServicesConfig;
import mx.gob.hidalgo.repss.planeacion.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "mx.gob.hidalgo.repss.planeacion.controller" })
@Import({ WebConfig.class, PersistenceConfig.class, ServicesConfig.class,
        SecurityConfig.class, InfrastructureConfig.class, MailConfig.class})
@EnableAutoConfiguration(exclude = {VelocityAutoConfiguration.class})
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}