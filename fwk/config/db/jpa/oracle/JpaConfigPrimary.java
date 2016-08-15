package zany.fwk.config.db.jpa.oracle;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ch.qos.logback.classic.Logger;

@Profile("local")
@Configuration
@EnableTransactionManagement
@Lazy
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryFirst", transactionManagerRef = "transactionManagerJpaFirst", basePackages = {
    "zany.repository.jpa" })
public class JpaConfigPrimary {

    private static final Logger log = (Logger) LoggerFactory.getLogger(JpaConfigPrimary.class);

    @Autowired
    JpaVendorAdapter jpaVendorAdapter;

    @Primary
    @Bean(name = "entityManagerFactoryFirst")
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                     @Qualifier("jpaFirstDataSource") DataSource dataSource,
                                                     @Value("${datasource.jpa-primary.database-platform}") String dialect) {

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", dialect);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("zany.repository.entity");
        emf.setPersistenceUnitName("jpaFirst");
        emf.setJpaProperties(props);
        emf.afterPropertiesSet();
        return emf.getObject();

        // return builder
        // .dataSource(dataSource)
        // .packages("zany.repository.jpa")
        // .persistenceUnit("firstJpa")
        // .build();
    }

    @Bean(name = "transactionManagerJpaFirst")
    public PlatformTransactionManager transactionManager(
                                                         @Qualifier("entityManagerFactoryFirst") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
