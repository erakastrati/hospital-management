package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostsService {

    public Page<PostDto> getAllPostsPaginated(Pageable pageable);
    public Page<PostDto> getPostsOfDoctor(Integer doctorId, Pageable pageable);
    public PostDto createPost(PostDto post, Integer doctorId);
    public PostDto getSpecificPost(String id);

}
