package com.example.postservice.component;

import lombok.Data;

import java.util.Date;

@Data
public class CommentEventModel {
    private String id;
    private String message;
    private String tweetid;
    private Date created;
}
