package com.msys.reactboot.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts")
    public Map<String, Object> getPosts(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        // 게시글 목록 가져오기
        List<Post> posts = postService.getPosts(page, size);

        // 총 게시글 수 가져오기
        long totalPosts = postService.getTotalPostsCount();

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("content", posts);
        response.put("totalPages", (totalPosts + size - 1) / size); // 총 페이지 수 계산
        response.put("totalPosts", totalPosts); // 총 게시글 수

        return response;
    }
}