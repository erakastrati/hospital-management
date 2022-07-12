package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.dtos.PostDto;
import com.ubt.hospitalmanagement.entities.Posts;
import com.ubt.hospitalmanagement.dtos.response.mappers.PostMapper;
import com.ubt.hospitalmanagement.repositories.PostRepository;
import com.ubt.hospitalmanagement.services.PostsService;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostRepository repository;
    private final UserService userService;

    public Page<PostDto> getAllPostsPaginated(Pageable pageable) {
        return repository.findAll(pageable).map(PostMapper::map);
    }

    public Page<PostDto> getPostsOfDoctor(Integer doctorId, Pageable pageable) {
        return repository.findByDoctorId(doctorId, pageable).map(PostMapper::map);
    }

    public PostDto createPost(PostDto post, Integer doctorId) {
        Posts savedPost = PostMapper.map(post);
        savedPost.setDoctorId(doctorId);
        return PostMapper.map(repository.save(savedPost));
    }

    public PostDto getSpecificPost(String id) {
        return PostMapper.map(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
