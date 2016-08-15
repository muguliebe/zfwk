package zany.fwk.config.db.jpa.mongo;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import ch.qos.logback.classic.Logger;
import zany.util.ZCode;

@Configuration
@EnableMongoRepositories(basePackages = "zany.repository.mongo", mongoTemplateRef = "externalMongoTemplate")
public class MongoConfig {

    private static final Logger log = (Logger) LoggerFactory.getLogger(MongoConfig.class);
    @Value("${mongo.primary.host}")
    private String host;

    @Value("${mongo.primary.port:27017}")
    private String port;

    @Value("${mongo.primary.database}")
    private String database;

    @Value("${mongo.primary.user-name}")
    private String userName;

    @Value("${mongo.primary.password}")
    private String password;

    @Autowired
    ZCode zcode;

    @Bean(name = "externalMongoClient")
    public MongoClient mongoClient() throws IOException {
        if (host == null) {
            host = "localhost";
        }
        log.info("=============== External Mongo Create ===============:{}:{}", host, port);
        password = zcode.decrypt(password);
        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
        ServerAddress serverAddress = new ServerAddress(host, Integer.parseInt(port));
        return new MongoClient(serverAddress, Arrays.asList(credential));
    }

    /**
     * mongo db facoty allowed only one
     * factory is in EmbeddedMongoConfig.java
     */
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, database);
    }

    @Autowired
    @Bean(name = "externalMongoTemplate")
    public MongoTemplate mongoTemplate(MongoClient mongoClient) throws IOException {
        return new MongoTemplate(mongoDbFactory(mongoClient()));
    }
}