package com.ubt.hospitalmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PatientDto {

    private String email;
    private String password;
    private List<String> diseases;


}
