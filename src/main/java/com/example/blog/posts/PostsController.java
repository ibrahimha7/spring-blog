package com.example.blog.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public List<Posts> getPosts() {
        return postsService.getPosts();
    }

    @PostMapping
    public void crateNewPost(@RequestBody Posts post){
        postsService.createPost(post);
    }
    @PutMapping
    public void updatePost(@RequestParam Long id,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String brief,
                           @RequestParam(required = false) String content){
        postsService.updatePost(id,title,brief,content);
    }
}
