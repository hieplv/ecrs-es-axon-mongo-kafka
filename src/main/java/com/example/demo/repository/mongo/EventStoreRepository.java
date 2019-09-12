package com.example.demo.repository.mongo;

import com.example.demo.entity.mongo.EventStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreRepository extends MongoRepository<EventStore, String> {

}
