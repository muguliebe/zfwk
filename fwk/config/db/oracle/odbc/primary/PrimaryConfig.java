package zany.fwk.config.db.oracle.odbc.primary;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import zany.fwk.config.db.oracle.odbc.base.DatabaseConfig;

@Profile("local")
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties(PrimaryDatabaseProperties.class)
public class PrimaryConfig extends DatabaseConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PrimaryDatabaseProperties localDatabaseProperties;

    @Override
    @Primary
    @Bean(name = "primaryDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        log.info("=============== DataSource Config Start ===============");
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(dataSource, localDatabaseProperties);
        log.info("=============== DataSource Config End   ===============");
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

}