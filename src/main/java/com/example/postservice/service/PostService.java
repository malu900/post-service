package com.example.postservice.service;


import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;
//    private final MongoOperations mongoOperations;

    public Post createPost(Post post) {
        return mongoTemplate.save(post, "post");
    }
    public Optional<Post> getPost(String id) {
        return postRepository.getPostById(id);
    }

//    public Comment createComment(Comment comment) {
//        return mongoTemplate.save(comment, "comment");
//    }
//
//    public Optional<Comment> getComment(String id) {
//        //        Comment comment = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Comment.class);
//        return commentRepository.findById(id);
//    }
}
