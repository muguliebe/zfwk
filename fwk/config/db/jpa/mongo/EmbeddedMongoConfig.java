package zany.fwk.config.db.jpa.mongo;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

import ch.qos.logback.classic.Logger;
import de.flapdoodle.embed.mongo.MongodExecutable;

@Profile("embeddedMongo")
@Configuration
@EnableMongoRepositories(basePackages = "zany.repository.embeddedmongo", mongoTemplateRef = "mongoTemplate")
public class EmbeddedMongoConfig {

    private static final Logger log              = (Logger) LoggerFactory.getLogger(EmbeddedMongoConfig.class);
    private static final String DB_NAME          = "test";
    private static final int    DB_PORT          = 27017;
    private static final String DB_HOST          = "mugu.iptime.org";
    private static final String DB_COLLECTION    = "products";

    private MongodExecutable    mongodExecutable = null;

    @Bean(name = "mongoClient")
    public MongoClient mongoClient() throws IOException {
        log.info("=============== Embedded Mongo Create ===============");
        return new MongoClient(DB_HOST, DB_PORT);
    }

    @Autowired
    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, DB_NAME);
    }

    @Autowired
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoClient mongoClient) throws IOException {
        return new MongoTemplate(mongoDbFactory(mongoClient()));
    }

    @PreDestroy
    public void shutdownEmbeddedMongoDB() {
        if (this.mongodExecutable != null) {
            this.mongodExecutable.stop();
        }
    }

    // @Autowired
    // private MongoProperties properties;
    // @Autowired(required = false)
    // private MongoClientOptions options;
    //
    // @Bean(destroyMethod = "close")
    // public Mongo mongo(MongodProcess mongodProcess) throws IOException {
    // Net net = mongodProcess.getConfig().net();
    // properties.setHost(net.getServerAddress().getHostName());
    // properties.setPort(net.getPort());
    // return properties.createMongoClient(this.options);
    // }
}