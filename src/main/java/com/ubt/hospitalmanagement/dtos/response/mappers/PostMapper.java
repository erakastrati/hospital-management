package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.PostDto;
import com.ubt.hospitalmanagement.entities.Posts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostMapper {

    public static PostDto map(Posts post) {
        return PostDto.builder()
                .description(post.getDescription())
                .id(post.getId())
                .category(post.getCategory())
                .owner(Optional.ofNullable(post.getOwner()).map(UserMapper::map).orElse(null))
                .title(post.getTitle())
                .build();
    }

    public static Posts map(PostDto post) {
        return Posts.builder()
                .description(post.getDescription())
                .id(post.getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .build();
    }

    public static List<PostDto> map(List<Posts> posts) {
        List<PostDto> mappedPosts = new ArrayList<>();
        for(Posts post : posts) {
            mappedPosts.add(map(post));
        }
        return mappedPosts;
    }
}
