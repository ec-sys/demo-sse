package com.demo.sse.server.core.listener;

import com.demo.sse.server.core.event.WiredDataChangeEvent;
import com.demo.sse.server.model.Grade;
import com.demo.sse.server.model.Office;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Consumer;

@EnableAsync
@Configuration
public class CDCEventListener {
    @Autowired
    MongoClient mongoClient;

    @Async
    @EventListener
    public void registrationDateChangeListener(WiredDataChangeEvent event) throws Exception {
        MongoDatabase db = mongoClient.getDatabase(event.getDbName());
        switch (event.getCollectionName()) {
            case "offices":
                MongoCollection<Office> office = db.getCollection(event.getCollectionName(), Office.class);
                office.watch().forEach(printEventOffice());
                break;
            case "grades":
                MongoCollection<Grade> grade = db.getCollection(event.getCollectionName(), Grade.class);
                grade.watch().forEach(printEventGrade());
                break;
        }
    }

    private Consumer<ChangeStreamDocument<Office>> printEventOffice() {
        return System.out::println;
    }
    private Consumer<ChangeStreamDocument<Grade>> printEventGrade() {
        return System.out::println;
    }
}
