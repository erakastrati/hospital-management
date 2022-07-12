package com.ubt.hospitalmanagement.dtos;

import com.ubt.hospitalmanagement.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentDto {

    private Long id;

    private LocalDate date;

    private UserDto patient;
//
//    private UserDto doctor;

//    private String doctorEmail;
//    private String doctorFirstName;
//    private String

    private String description;

    private SlotDto slot;

    private AppointmentStatus status;
}
