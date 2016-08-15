package zany.fwk.config.db.oracle.odbc.base;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public abstract class MyBatisConfig {
	
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
	public static final String BASE_PACKAGE = "zany.repository";
	public static final String TYPE_ALIASES_PACKAGE = "zany.repository.entity";
	public static final String CONFIG_LOCATION_PATH = "classpath:META-INF/mybatis/mybatis-config.xml";
	public static final String MAPPER_LOCATIONS_PATH = "classpath:META-INF/mybatis/mapper/**/*.xml";
	
	/**
	 * Configure sql session factory.
	 *
	 * @param sessionFactoryBean the session factory bean
	 * @param dataSource the data source
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
	    log.info("=============== configSqlSessionFactory Start ===============");
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
		sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
		log.info("=============== configSqlSessionFactory End   ===============");
	}
	
}