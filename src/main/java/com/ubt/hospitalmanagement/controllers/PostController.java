package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.PostDto;
import com.ubt.hospitalmanagement.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostsService service;

    @PostMapping("/{id}")
    public ResponseEntity<PostDto> createPostForDoctor(@PathVariable Integer id, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(service.createPost(postDto, id));
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getAllPostsPaginated(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok(service.getAllPostsPaginated(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getSpecificPost(@PathVariable String id) {
        return ResponseEntity.ok(service.getSpecificPost(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Page<PostDto>> getAllPostsForDoctorPaginated(@PathVariable Integer id, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok(service.getPostsOfDoctor(id, pageable));
    }
}
