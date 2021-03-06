package com.ubt.hospitalmanagement.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class AvailableSlotsRequests {
    private Integer doctorId;
    private LocalDate date;
}
