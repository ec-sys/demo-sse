package com.demo.sse.server.controller.rest;

import com.demo.sse.server.core.event.DownloadCSVDoneEvent;
import com.demo.sse.server.model.Notification;
import com.demo.sse.server.service.DownloadCSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
public class DownloadCSVController {

//    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
//
//    @Autowired
//    DownloadCSVService downloadCSVService;
//
//    @GetMapping("/api/csv/download")
//    public SseEmitter downloadCSV(@RequestParam String officeUserId) throws Exception {
//        downloadCSVService.downloadCsv(officeUserId);
//
//        SseEmitter emitter = new SseEmitter();
//        this.emitters.add(emitter);
//
//        emitter.onCompletion(() -> this.emitters.remove(emitter));
//        emitter.onTimeout(() -> {
//            emitter.complete();
//            this.emitters.remove(emitter);
//        });
//
//        return emitter;
//    }
//
//    @EventListener
//    public void onNotification(Notification notification) {
//        List<SseEmitter> deadEmitters = new ArrayList<>();
//        this.emitters.forEach(emitter -> {
//            try {
//                emitter.send(notification);
//            } catch (Exception e) {
//                deadEmitters.add(emitter);
//            }
//        });
//        this.emitters.remove(deadEmitters);
//    }
}
