package com.example.demo.config;

import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoEventStoreConfig {
    @Value("${mongo.host:127.0.0.1}")
    private String mongoHost;

    @Value("${mongo.port:27017}")
    private int mongoPort;

    @Value("${mongo.db:test}")
    private String mongoDB;

    @Bean
    public MongoSagaStore sagaStore() {
        return new MongoSagaStore(axonMongoTemplate());
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return new MongoTokenStore(axonMongoTemplate(), serializer);
    }

    @Bean
    public EventStorageEngine eventStorageEngine(Serializer serializer) {
        return new MongoEventStorageEngine(serializer, null, axonMongoTemplate(), new DocumentPerEventStorageStrategy());
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return new DefaultMongoTemplate(mongo(), mongoDB);
    }

    @Bean
    public MongoClient mongo() {
        MongoFactory mongoFactory = new MongoFactory();
        mongoFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        return mongoFactory.createMongo();
    }
}
