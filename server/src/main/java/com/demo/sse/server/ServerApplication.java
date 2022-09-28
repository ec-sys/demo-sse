package com.demo.sse.server;

import com.demo.sse.server.core.publisher.CustomSpringEventPublisher;
import com.demo.sse.server.core.publisher.WiredDataChangeEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    @Autowired
    WiredDataChangeEventPublisher wiredDataChangeEventPublisher;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("event 1");
        wiredDataChangeEventPublisher.publishCustomEvent("registration", "offices");
        System.out.println("event 2");
        wiredDataChangeEventPublisher.publishCustomEvent("registration", "grades");
    }
}
