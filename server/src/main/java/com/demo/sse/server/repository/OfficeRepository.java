package com.demo.sse.server.repository;

import com.demo.sse.server.model.Office;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends MongoRepository<Office, String> {
}
