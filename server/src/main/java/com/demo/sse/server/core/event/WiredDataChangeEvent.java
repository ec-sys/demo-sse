package com.demo.sse.server.core.event;

import org.springframework.context.ApplicationEvent;

public class WiredDataChangeEvent extends ApplicationEvent {

    private String dbName;
    private String collectionName;

    public WiredDataChangeEvent(Object source) {
        super(source);
    }

    public WiredDataChangeEvent(Object source, String dbName, String collectionName) {
        super(source);
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
