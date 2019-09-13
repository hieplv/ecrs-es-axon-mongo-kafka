package com.example.demo.repository.mongo;

import com.example.demo.aggregate.CustomerAggregate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAggregateRepository extends MongoRepository<CustomerAggregate,String> {

}
