package com.example.postservice.service;


import com.example.postservice.component.CommentEventModel;
import com.example.postservice.model.Comment;
import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Optional<Post> optionalPost = this.getPost(data.getTweetid());
        Post post = new Post();
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
        }
        post.setCommentCount(post.getCommentCount() +1);

        Comment comment = new Comment();
        comment.setCreated(data.getCreated());
        comment.setId(data.getId());
        comment.setMessage(data.getMessage());
        comment.setTweetid(data.getTweetid());
        comment.setUserId(data.getUserId());

        List<Comment> commentList = new ArrayList<>();
        if(post.getCommentList() != null) commentList.addAll(post.getCommentList());
        commentList.add(comment);
        post.setCommentList(commentList);

        mongoTemplate.save(post, "post");
    }

}
