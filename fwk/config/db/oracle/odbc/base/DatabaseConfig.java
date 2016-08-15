package zany.fwk.config.db.oracle.odbc.base;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import zany.util.ZCode;

public abstract class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public abstract DataSource dataSource();

    @Autowired
    ZCode zcode;

    protected void configureDataSource(
                                       org.apache.tomcat.jdbc.pool.DataSource dataSource,
                                       DatabaseProperties databaseProperties) {

        log.info("=============== DataSource Setting Start =============== ");
        log.info("DataSource          = {}", dataSource);
        log.info("ConfigureDataSource = {}", databaseProperties);
        log.info("DataSource URL      = {}", databaseProperties.getUrl());
        dataSource.setDriverClassName(databaseProperties.getDriverClassName());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUserName());
        dataSource.setPassword(zcode.decrypt(databaseProperties.getPassword()));
        dataSource.setMaxActive(databaseProperties.getMaxActive());
        dataSource.setMaxIdle(databaseProperties.getMaxIdle());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setMaxWait(databaseProperties.getMaxWait());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        log.info("=============== DataSource Setting End =============== ");
    }
}