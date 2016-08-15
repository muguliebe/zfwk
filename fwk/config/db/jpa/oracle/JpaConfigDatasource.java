package zany.fwk.config.db.jpa.oracle;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ch.qos.logback.classic.Logger;
import zany.util.ZCode;

@Profile("local")
@Configuration
public class JpaConfigDatasource {

    static final Logger log = (Logger) LoggerFactory.getLogger(JpaConfigDatasource.class);

    @Autowired
    ZCode zcode;

    @Bean(name = "jpaFirstDataSource")
    public DataSource dataSource(
                                 @Value("${datasource.jpa-primary.database-platform}") String dialect,
                                 @Value("${datasource.jpa-primary.driver-class-name}") String driverClassName,
                                 @Value("${datasource.jpa-primary.url}") String url,
                                 @Value("${datasource.jpa-primary.user-name}") String username,
                                 @Value("${datasource.jpa-primary.password}") String password) {

        log.info("=============== DataSource[JPA] Config Start ===============");
        log.info("{}", dialect);
        log.info("{}", driverClassName);
        log.info("{}", url);
        log.debug("{}", username);
        log.debug("{}", password);
        log.info("=============== DataSource[JPA] Config End   ===============");

        /** if u wanna with props and use like below */
        Properties props = new Properties();
        props.setProperty("initial-size", "10");
        props.setProperty("max-active", "10");
        props.setProperty("min-idle", "10");
        props.setProperty("max-idle", "10");
        props.setProperty("max-wait", "3000");
        props.setProperty("show-sql", "false");
        props.setProperty("generate-ddl", "false");


        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setConnectionProperties(props);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(zcode.decrypt(password));
        return ds;
        /**
         * /
         * 
         * return DataSourceBuilder
         * .create()
         * .driverClassName(driverClassName)
         * .url(url)
         * .username(username)
         * .password(ZCode.decrypt(password))
         * .build()
         * ;
         * /
         **/
    }
}
