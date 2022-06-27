package com.ubt.hospitalmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String id;

    private Integer doctorId;

    private String title;
    private String category;
    private String description;
    private LocalDate publishDate;
}
