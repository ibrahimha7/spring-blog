package com.example.blog.posts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    @Autowired
    public PostsService(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    public List<Posts> getPosts() {
        return postsRepository.findAll();
    }

    public void createPost(Posts post) {
        postsRepository.save(post);
    }

    public void updatePost(Long id, String title, String brief, String content) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "Post not exist"
        ));

        if(title !=null && title.length() > 0 && !Objects.equals(posts.getTitle(),title)) {
            posts.setTitle(title);
        }

        if(brief !=null && brief.length() > 0 && !Objects.equals(posts.getContent(),brief)) {
            posts.setBrief(brief);
        }
        if(content !=null && content.length() > 0 && !Objects.equals(posts.getContent(),content)) {
            posts.setContent(content);
        }
    }
}
