package com.demo.sse.server.config;

import com.demo.sse.server.model.Office;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class OfficeChangeStream implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    MongoClient mongoClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        MongoDatabase db = mongoClient.getDatabase("registration");
//        MongoCollection<Office> offices = db.getCollection("offices", Office.class);
//        offices.watch().forEach(printEventOffice());
    }
    private Consumer<ChangeStreamDocument<Office>> printEventOffice() {
        return System.out::println;
    }
}
