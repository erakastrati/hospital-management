package com.ubt.hospitalmanagement.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String message;
}
