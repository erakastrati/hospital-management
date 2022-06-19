package com.ubt.hospitalmanagement.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnoseDto {

    private Integer patientId;
    private String diagnose;
}
