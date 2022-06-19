package com.ubt.hospitalmanagement.dtos.requests;

import com.ubt.hospitalmanagement.dtos.SlotDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AppointmentRequestDto {

    private Integer doctorId;
    private LocalDate date;
    private String description;
    private SlotDto slot;
}
