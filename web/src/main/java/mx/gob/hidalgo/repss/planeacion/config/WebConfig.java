package mx.gob.hidalgo.repss.planeacion.config;

import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Created by kkimvazquezangeles on 28/11/14.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Resource
    protected Environment env;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/photo/**")
            .addResourceLocations("file:" + env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO), "classpath:/static/img/photo/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
}