package com.demo.sse.server.config;

import com.demo.sse.server.model.Grade;
import com.demo.sse.server.model.Office;
import com.demo.sse.server.model.Room;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@Slf4j
public class ChangeStream implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    MongoClient mongoClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        MongoDatabase db = mongoClient.getDatabase("registration");
//        MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
//        MongoCollection<Office> offices = db.getCollection("offices", Office.class);
//        MongoCollection<Room> rooms = db.getCollection("rooms", Room.class);
        List<Bson> pipeline;

        // Only uncomment one example at a time. Follow instructions for each individually then kill all remaining processes.

        /** => Example 1: print all the write operations.
         *  => Start "ChangeStreams" then "MappingPOJOs" to see some change events.
         */
//        grades.watch().forEach(printEventGrade());
//        offices.watch().forEach(printEventOffice());
//        rooms.watch().forEach(printEventRoom());

        /** => Example 2: print only insert and delete operations.
         *  => Start "ChangeStreams" then "MappingPOJOs" to see some change events.
         */
        // pipeline = singletonList(match(in("operationType", asList("insert", "delete"))));
        // grades.watch(pipeline).forEach(printEvent());

        /** => Example 3: print only updates without fullDocument.
         *  => Start "ChangeStreams" then "Update" to see some change events (start "Create" before if not done earlier).
         */
        // pipeline = singletonList(match(eq("operationType", "update")));
        // grades.watch(pipeline).forEach(printEvent());

        /** => Example 4: print only updates with fullDocument.
         *  => Start "ChangeStreams" then "Update" to see some change events.
         */
        // pipeline = singletonList(match(eq("operationType", "update")));
        // grades.watch(pipeline).fullDocument(UPDATE_LOOKUP).forEach(printEvent());

        /**
         * => Example 5: iterating using a cursor and a while loop + remembering a resumeToken then restart the Change Streams.
         * => Start "ChangeStreams" then "Update" to see some change events.
         */
        // exampleWithResumeToken(grades);
    }
    private Consumer<ChangeStreamDocument<Office>> printEventOffice() {
        return System.out::println;
    }
    private Consumer<ChangeStreamDocument<Grade>> printEventGrade() {
        return System.out::println;
    }
    private Consumer<ChangeStreamDocument<Room>> printEventRoom() { return System.out::println; }

    private void change1() {
        ConnectionString connectionString = new ConnectionString("mongodb://root:password123@localhost:27020/registration?authSource=admin");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase("registration");
            MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
            List<Bson> pipeline;

            // Only uncomment one example at a time. Follow instructions for each individually then kill all remaining processes.

            /** => Example 1: print all the write operations.
             *  => Start "ChangeStreams" then "MappingPOJOs" to see some change events.
             */
            // grades.watch().forEach(printEvent());
        }
    }
}
