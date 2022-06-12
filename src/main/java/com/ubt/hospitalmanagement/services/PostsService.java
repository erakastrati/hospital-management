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

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostRepository repository;
    private final UserService userService;

    public Page<PostDto> getAllPostsPaginated(Pageable pageable) {
        return repository.findAll(pageable).map(PostMapper::map);
    }

    public Page<PostDto> getPostsOfDoctor(Long doctorId, Pageable pageable) {
        return repository.findByOwner(userService.getDoctorById(doctorId), pageable).map(PostMapper::map);
    }

    public PostDto createPost(PostDto post, Long doctorId) {
        User doctor = userService.getDoctorById(doctorId);
        Posts savedPost = PostMapper.map(post);
        savedPost.setOwner(doctor);
        return PostMapper.map(repository.save(savedPost));
    }
}
