package com.demo.sse.server.core.publisher;

import com.demo.sse.server.core.event.WiredDataChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WiredDataChangeEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(String dbName, String collectionName) {
        System.out.println("Publishing custom cdc event. ");
        WiredDataChangeEvent eventPublisher = new WiredDataChangeEvent(this, dbName, collectionName);
        applicationEventPublisher.publishEvent(eventPublisher);
    }
}