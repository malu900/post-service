package com.example.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

@Document
@Data
public class Post {
    @Id
    private String id;
    private String message;
    private Long userId;
    @Indexed
    @CreatedDate
    private Date created = new Date();

    public Post(String message) {
        this.message = message;
    }
    public Post() {}
}
