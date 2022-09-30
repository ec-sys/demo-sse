package com.demo.sse.server.service;

import com.demo.sse.server.core.event.DownloadCSVDoneEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DownloadCSVService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void downloadCsv(String officeUserId) throws InterruptedException {
        String fileUrl = "File - " + officeUserId;
        log.info("start downloading " + fileUrl);
        Thread.sleep(2000);
        log.info("done downloading " + fileUrl);
        DownloadCSVDoneEvent event = new DownloadCSVDoneEvent(this, fileUrl);
        eventPublisher.publishEvent(event);
    }
}
