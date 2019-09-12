package com.example.demo.entity.mongo;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
public class EventStore {
    @Id
    protected UUID id;
    protected UUID aggregateId;
    protected String aggregateType;
    protected String payLoad;
    protected int version;

    @Basic
    protected Timestamp createdAt;

    public EventStore() {
        this.id = UUID.randomUUID();
        this.version = 1;
    }

    public EventStore(UUID id, UUID aggregateId, String aggregateType, String payLoad, int version) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.payLoad = payLoad;
        this.version = version;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.from(Instant.now());
    }
}
