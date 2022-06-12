package com.example.postservice.model;


import lombok.Data;

@Data public class SentimentEvent {
    private String id;
    private String sentiment;
    private String sentimentId;
}
