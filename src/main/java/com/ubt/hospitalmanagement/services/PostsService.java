package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.PostDto;
import com.ubt.hospitalmanagement.entities.Posts;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.dtos.response.mappers.PostMapper;
import com.ubt.hospitalmanagement.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PostsService {

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
