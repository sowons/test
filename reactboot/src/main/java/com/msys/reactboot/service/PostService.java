package com.msys.reactboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.msys.reactboot.domain.Post;
import com.msys.reactboot.repository.PostRepository;

public class PostService {
 @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return postRepository.findAll(pageable).getContent();
    }
}
