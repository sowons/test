package com.msys.reactboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msys.reactboot.domain.Post;
import com.msys.reactboot.service.PostService;

/**
 * PostController
 */
@RestController
@RequestMapping(value = "/list")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size) {
    return postService.getPosts(page, size);
    }
    
}