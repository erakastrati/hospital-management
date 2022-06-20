package com.ubt.hospitalmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;

    private UserDto owner;

    private String title;
    private String category;
    private String description;
}
