package zany.fwk.config.db.oracle.odbc.second;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zany.fwk.config.db.oracle.odbc.base.MyBatisConfig;
import zany.repository.support.Nxm;

@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = Nxm.class, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class secondMyBatisConfig extends MyBatisConfig {

    @Bean
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource)
        throws Exception {
        log.info("=============== nxmSqlSessionFactory Start ===============");
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, dataSource);
        log.info("=============== nxmSqlSessionFactory End   ===============");
        return sessionFactoryBean.getObject();
    }

}