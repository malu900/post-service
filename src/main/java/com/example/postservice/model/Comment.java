package com.example.postservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;
//import java.util.Date;

@Document
@Data
public class Comment {

    @Id private String id;
    private String message;
    private String tweetid;
    private Date created;

    public Comment(String message) {
        this.message = message;
    }
    public Comment() {}
}
