package com.ubt.hospitalmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private Integer doctorId;
    private String author;

    private String title;
    private String category;
    private String description;
    private LocalDate publishDate;

}
