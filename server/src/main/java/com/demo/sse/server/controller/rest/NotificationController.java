package com.demo.sse.server.controller.rest;

import com.demo.sse.server.model.Notification;
import com.demo.sse.server.service.NotificationJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
public class NotificationController {
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    NotificationJobService notificationJobService;

    @GetMapping("/api/new_notification")
    public SseEmitter getNewNotification() throws Exception {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);

        emitter.onCompletion(() -> {
            log.info("completed: " + emitters.size());
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
            log.info("timeout: " + emitters.size());
        });

        return emitter;
    }

    @GetMapping("/api/send_notification")
    @ResponseBody
    public String sendNewNotification() throws Exception {
        notificationJobService.publishJobNotifications();
        return "send done";
    }

    @EventListener
    public void onNotification(Notification notification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        log.info("emitters size: {}", emitters.size());
        this.emitters.forEach(emitter -> {
            try {
                log.info("notification : {}" + notification);
                emitter.send(notification);
            } catch (Exception e) {
                e.printStackTrace();
                deadEmitters.add(emitter);
            }
        });
        this.emitters.remove(deadEmitters);
    }
}
