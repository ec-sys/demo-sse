package com.demo.sse.server.config;

import com.demo.sse.server.model.Office;
import com.demo.sse.server.repository.OfficeRepository;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.Subscription;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Component
@Slf4j
public class DateFaker implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    MessageListenerContainer messageListenerContainer;
    @Autowired
    MongoClient mongoClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    }

    private void listenChangeDateOffice() {
        MessageListener<ChangeStreamDocument<Document>, Office> messageListener = (message) -> {
            System.out.println("Hello " + message.getBody());
        };

        ChangeStreamRequest<Office> request = ChangeStreamRequest.builder()
                .collection("offices")
                .filter(Aggregation.newAggregation(match(Criteria.where("operationType").is("insert"))))
                .publishTo(messageListener)
                .build();

        Subscription subscription = messageListenerContainer.register(request, Office.class);

        MongoCollection<Office> grades = mongoClient.getDatabase("registration").getCollection("offices", Office.class);
        ChangeStreamIterable<Office> changeStream = grades.watch();
        changeStream.forEach((Consumer<ChangeStreamDocument<Office>>) System.out::println);
    }

    private void fakerOffice() {
        Office office = new Office();
        office.setName("BV5");
        office.setAddress("Ha Noi");
        officeRepository.save(office);
    }
}
