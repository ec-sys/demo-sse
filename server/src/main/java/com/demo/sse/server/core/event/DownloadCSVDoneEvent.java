package com.demo.sse.server.core.event;

import org.springframework.context.ApplicationEvent;

public class DownloadCSVDoneEvent extends ApplicationEvent {
    private String fileUrl;

    public DownloadCSVDoneEvent(Object source, String fileUrl) {
        super(source);
        this.fileUrl = fileUrl;
    }
    public String getFileUrl() {
        return fileUrl;
    }
}
