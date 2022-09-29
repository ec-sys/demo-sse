package com.demo.sse.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private ObjectId id;
    @BsonProperty(value = "student_id")
    private Double studentId;
    @BsonProperty(value = "class_id")
    private Double classId;

}
