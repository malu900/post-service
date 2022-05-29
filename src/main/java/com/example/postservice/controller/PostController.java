package com.example.postservice.controller;

import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody Post params){
        Post post = postService.createPost(params);
        if(post == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Optional<Post> post = postService.getPost(id);
        if(post.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.ok().body(post.get());
    }
    @PutMapping
    public ResponseEntity<Post> updatePost(Post params) {
        Post post = postService.createPost(params);
        if(post == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
