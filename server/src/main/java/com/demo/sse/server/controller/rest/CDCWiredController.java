package com.demo.sse.server.controller.rest;

import com.demo.sse.server.core.publisher.WiredDataChangeEventPublisher;
import com.demo.sse.server.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CDCWiredController {

    @Autowired
    WiredDataChangeEventPublisher cdcWiredPublisher;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/api/cdc/publisher")
    @ResponseBody
    public String addFoo(@RequestParam String dbName, @RequestParam String collectionName) {
        cdcWiredPublisher.publishCustomEvent(dbName, collectionName);
        return "DONE - " + dbName + "-" + collectionName;
    }

    @GetMapping("/api/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now().toString())
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    @GetMapping("/api/push-notify")
    @ResponseBody
    public String pushNotify() {
        Integer jobId = Notification.getNextJobId();
        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());
        eventPublisher.publishEvent(nStarted);
        return "DONE - " + jobId;
    }
}
