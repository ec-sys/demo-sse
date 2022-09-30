package com.demo.sse.server.core.listener;

import com.demo.sse.server.core.event.WiredDataChangeEvent;
import com.demo.sse.server.model.Grade;
import com.demo.sse.server.model.Office;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class WiredDataChangeEventListener implements ApplicationListener<WiredDataChangeEvent> {
    @Autowired
    MongoClient mongoClient;

    @Override
    public void onApplicationEvent(WiredDataChangeEvent event) {
//        MongoDatabase db = mongoClient.getDatabase(event.getDbName());
//        switch (event.getCollectionName()) {
//            case "offices":
//                MongoCollection<Office> office = db.getCollection(event.getCollectionName(), Office.class);
//                office.watch().forEach(printEventOffice());
//                break;
//            case "grades":
//                MongoCollection<Grade> grade = db.getCollection(event.getCollectionName(), Grade.class);
//                grade.watch().forEach(printEventGrade());
//                break;
//        }
    }

    private Consumer<ChangeStreamDocument<Office>> printEventOffice() {
        return System.out::println;
    }
    private Consumer<ChangeStreamDocument<Grade>> printEventGrade() {
        return System.out::println;
    }
}
