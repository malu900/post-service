package com.example.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class Post {
    private String id;
    private String message;
    private String userId;
    private String sentiment;
    private Integer commentCount;
    private List<Comment> commentList;
    @Indexed
    @CreatedDate
    private Date created = new Date();

    public Post(String message) {
        this.message = message;
    }
    public Post() {}
}
