package mx.gob.hidalgo.repss.planeacion.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"mx.gob.hidalgo.repss.planeacion.repositories"})
@PropertySource("classpath:application.properties")
public class PersistenceConfig {

    private static final String PROPERTY_SPRING_DATASOURCE_DRIVER   = "spring.datasource.driverClassName";
    private static final String PROPERTY_SPRING_DATASOURCE_URL      = "spring.datasource.url";
    private static final String PROPERTY_SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    private static final String PROPERTY_SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";

    private static final String PROPERTY_SPRING_JPA_SHOWSQL         = "spring.jpa.showSql";
    private static final String PROPERTY_SPRING_JPA_HB_GENERATEDDL  = "spring.jpa.hibernate.generateDdl";
    private static final String PROPERTY_SPRING_JPA_DATABASE        = "spring.jpa.dataBase";

    @Resource
    protected Environment env;

    @Bean
    @Profile("preprobd")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_SPRING_DATASOURCE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_SPRING_DATASOURCE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_SPRING_DATASOURCE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_SPRING_DATASOURCE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("mx.gob.hidalgo.repss.planeacion.model");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty(PROPERTY_SPRING_JPA_SHOWSQL)));
        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getRequiredProperty(PROPERTY_SPRING_JPA_HB_GENERATEDDL)));
        hibernateJpaVendorAdapter.setDatabase(Database.valueOf(env.getRequiredProperty(PROPERTY_SPRING_JPA_DATABASE)));
        return hibernateJpaVendorAdapter;
    }

    @Bean
    @Profile({"test", "devbd"})
    public DataSource dataSourceDev() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        //builder.addScript("classpath:/mx/gob/hidalgo/repss/planeacion/scripts/h2/ddl/user.sql");
        //builder.addScript("classpath:/mx/gob/hidalgo/repss/planeacion/scripts/h2/ddl/sequence.sql");
        //builder.addScript("classpath:/mx/gob/hidalgo/repss/planeacion/scripts/h2/ddl/constraints.sql");

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    @Profile({"test", "devbd"})
    public DataSourceInitializer dataSourceInitializerConfigurationDev() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/h2/dml/user.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/h2/dml/departamento.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/h2/dml/localidad.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/h2/dml/persona.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/h2/dml/archivo.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSourceDev());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    @Profile("dmlpreprobd")
    public DataSourceInitializer dataSourceInitializerConfiguration() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/mx/gob/hidalgo/repss/planeacion/scripts/postgresql/user.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
