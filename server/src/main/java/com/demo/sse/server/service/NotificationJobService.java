package com.demo.sse.server.service;

import com.demo.sse.server.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class NotificationJobService {

    public final ApplicationEventPublisher eventPublisher;

    public NotificationJobService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

//    @Scheduled(fixedRate = 4000, initialDelay = 2000)
//    public void publishJobNotifications() throws InterruptedException {
//        Integer jobId = Notification.getNextJobId();
//        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());
//        this.eventPublisher.publishEvent(nStarted);
//        Thread.sleep(2000);
//        Notification nFinished = new Notification("Job No. " + jobId + " finished.", new Date());
//        this.eventPublisher.publishEvent(nFinished);
//    }

    @Async
    public void publishJobNotifications() throws InterruptedException {
        Integer jobId = Notification.getNextJobId();
        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());
        log.info(nStarted.getText());
        this.eventPublisher.publishEvent(nStarted);

        Thread.sleep(2000);

        Notification nFinished = new Notification("Job No. " + jobId + " finished.", new Date());
        log.info(nFinished.getText());
        this.eventPublisher.publishEvent(nFinished);
    }
}