package com.example.postservice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document
@Data
public class Post {
    private String id;
    private String message;
    private Long userId;
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
