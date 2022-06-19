package com.ubt.hospitalmanagement.dtos.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String mobileNumber;
    private String email;
    private String password;
    private boolean doctor;
}
