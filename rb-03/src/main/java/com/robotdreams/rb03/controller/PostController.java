package com.robotdreams.rb03.controller;

import com.robotdreams.rb03.model.Post;
import com.robotdreams.rb03.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final RestTemplate restTemplate;
    private final PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",
                Post[].class);
        List<Post> postList = Arrays.asList(posts);
        postList.stream().forEach(post -> {
            post.setInsertDate(LocalDateTime.now());
            postRepository.save(post);
        });
        return postList;
    }
}
