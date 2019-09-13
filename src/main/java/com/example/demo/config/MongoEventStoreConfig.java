package com.example.demo.config;

import com.mongodb.*;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class MongoEventStoreConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

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

    @Bean(name = "axonMongoTemplate")
    public MongoTemplate axonMongoTemplate() {
        MongoTemplate template = new DefaultMongoTemplate(mongoClient(), mongoDbName);
        return template;
    }

    @Bean
    public MongoClient mongoClient(){
        MongoClientURI uri = new MongoClientURI(mongoUrl);
        MongoClient mongoClient = new MongoClient(uri);

        return mongoClient;
    }
}
