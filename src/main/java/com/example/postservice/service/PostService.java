package com.example.postservice.service;


import com.example.postservice.component.CommentEventModel;
import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;
//    private final MongoOperations mongoOperations;

    public Post createPost(Post post) {
        post.setCommentCount(0);
        return mongoTemplate.save(post, "post");
    }
    public Optional<Post> getPost(String id) {
        return postRepository.getPostById(id);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @KafkaListener(topics = "topicTwo", groupId = "test")
    void updateCommentPost(CommentEventModel data) {
        Optional<Post> post = this.getPost(data.getTweetid());
        if (post.isEmpty()) return;
        post.get().setCommentCount(+1);
        System.out.println(post.get());
        System.out.println(data);
        mongoTemplate.save(post.get(), "post");
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
