package com.ubt.hospitalmanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisseaseDto {
    private String patientUuid;
    private String dissease;
}
