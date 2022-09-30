package com.demo.sse.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("offices")
@AllArgsConstructor
@NoArgsConstructor
public class Office {
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "address")
    private String address;
}
