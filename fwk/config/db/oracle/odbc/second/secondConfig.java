package zany.fwk.config.db.oracle.odbc.second;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import zany.fwk.config.db.oracle.odbc.base.DatabaseConfig;

@Profile("local")
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(secondDatabaseProperties.class)
public class secondConfig extends DatabaseConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private secondDatabaseProperties nxmDevDatabaseProperties;

    @Override
    @Bean(name = "secondDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        log.info("=============== nxmDataSource Config Start ===============");
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(dataSource, nxmDevDatabaseProperties);
        log.info("=============== nxmDataSource Config End   ===============");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        log.info("=============== nxmDataSource TransactionManager Start ===============");
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        log.info("=============== nxmDataSource TransactionManager End   ===============");
        return transactionManager;
    }

}