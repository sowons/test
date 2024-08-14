package com.msys.reactboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msys.reactboot.domain.Post;
import com.msys.reactboot.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // 게시글 목록 가져오기
    public List<Post> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return postRepository.findAll(pageable).getContent();
    }

    // 총 게시글 수 가져오기
    public long getTotalPostsCount() {
        return postRepository.count(); // JpaRepository의 count() 메서드 사용
    }
}