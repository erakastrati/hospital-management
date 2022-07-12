package com.ubt.hospitalmanagement.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class DoctorDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;


    private LocalDate birthDate;
    private String description;
    private List<String> experiences;
    private String gender;
}
