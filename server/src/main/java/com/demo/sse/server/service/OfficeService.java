package com.demo.sse.server.service;

import com.demo.sse.server.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepository;
}
