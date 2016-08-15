package zany.fwk;

import java.util.Arrays;
import java.util.Properties;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ch.qos.logback.classic.Logger;


/**
 * NXCUS Agent Main
 * 
 * @author 윤준호
 * @see
 * 
 *      <pre>
 * 
 *  빌드
 *      
 *          pom.xml 위치한 곳에서 아래 명령어 입력
 *          mvn clean package -DskipTests
 *      
 *  
 *  로컬에서 수행 시 필요한 것
 *      
 *          Active Proifle 지정
 *          Logback 파일 위치 지정
 *          application.yml 프로퍼티 파일 위치 지정
 *          Eclipse 에서 수행 시:
 *              
 *                  Eclipse Menu > Run > Run Configuration > Spring Boot Tab > Override Properties 에 아래 입력
 *                      
 *                          Property:spring.profiles.active, Value:local,db,batch
 *                          Property:spring.config.location, Value:/project-workspace/ifds-main/etc/
 *                      
 *                  Eclipse Menu > Run > Run Configuration > Arguments > Program Arguments 에 아래 입력
 *                      
 *                          --logging.config=/project-workspace/ifds-main/etc/logback.xml
 *                          
 *                      
 *              
 *          java command 수행 시:
 *              
 *                  java -jar -Dspring.profiles.active=local,db,batch -Dspring.config.location=/project-workspace/ifds-main/etc/ ifds-main-0.1.jar --logging.config=/project-workspace/ifds-main/etc/logback.xml
 *                  
 *              
 *      
 *  
 *  서버에서 수행 시
 *      
 *          java -jar -Dspring.profiles.active=local,db,batch -Dspring.config.location=./resources/ ifdsMain-0.2.jar --logging.config=./resources/logback.xml
 *      
 *  Log
 *      
 *          RunTime Log Level 변경 위해, jxmConfigurator 추가 되어 있음
 *          Service Log는 logback.xml 이 아닌 BaseService.java 소스 내에 선언된 별도의 Logger 로 기록 됨
 * 
 * 
 * 
 *      </pre>
 * 
 * @version
 * 
 *          <pre>
 *          160509 | 윤준호 | 최초생성
 *          </pre>
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@ComponentScan({ "zany.*, com.skcc.*, exam.*" })
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, EmbeddedMongoAutoConfiguration.class })

public class AppMain extends SpringBootServletInitializer {

    private static final Logger log = (Logger) LoggerFactory.getLogger(AppMain.class);

    /**
     * 외부 톰캣 배포 시 호출되는 함수
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        Properties props = new Properties();
        props.put("spring.profiles.active", "local,db,batch,deploy");
        props.put("spring.config.location", "etc/z/");
        props.put("logging.config", "etc/z/logback.xml");

        SpringApplicationBuilder _builder = builder.sources(AppMain.class).properties(props);
        return _builder;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        // ==========================================================================
        // Print Start Logo
        // ==========================================================================
        log.info("=============== zcommon START ===============");
        log.info(getLogo());

        // ==========================================================================
        // Set Properties
        // ==========================================================================
        setProperty();

        // ==========================================================================
        // Spring Boot Start
        // ==========================================================================
        ApplicationContext ctx = SpringApplication.run(AppMain.class, args);
        log.info("=============== args: {}", args);
        log.info("=============== {}" + ctx.getEnvironment());

        /**/
        // ==========================================================================
        // Logging Loaded Beans
        // ==========================================================================
        String[] beanNames = ctx.getBeanDefinitionNames();
        log.info("=============== Loaded Class Info Start =============== ");
        Arrays.sort(beanNames);
        Integer beanCount = 0;
        for (String beanName : beanNames) {
            log.trace(beanName);
            beanCount++;
        }
        log.info("=============== Loaded Class Info End =============== Total: {}", beanCount);

        // ==========================================================================
        // Logging Environments
        // ==========================================================================
        Environment env = (Environment) ctx.getBean("environment");
        for (String s : env.getActiveProfiles()) {
            log.info("profiles:" + s);
        }
        log.info("=============== Profiles End =============== ");

        log.info("=============== Environments =============== {}", env.toString());
        log.info("=============== Log Level    =============== {}",
            ((Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)).getLevel());

    }


    /**
     * Sets the property.
     */
    private static void setProperty() {

        // Prefer IPv4
        System.setProperty("java.net.preferIPv4Stack", "true");

    }

    /**
     * Destory.
     */
    @PreDestroy
    public void destory() {
        log.info("=============== Application Exit =============== ");

    }

    /**
     * Gets the dog.
     *
     * @return the dog
     */
    private static String getLogo() {
        StringBuffer sbLogo = new StringBuffer();
        sbLogo.append("\n");
        return sbLogo.toString();
    }

}
