package com.demo.sse.server.core.event;

import lombok.Data;

@Data
public class CSVServerEvent {
    private String message;
    private String fileUrl;
    private String eventType;
}
