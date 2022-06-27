package com.ubt.hospitalmanagement.dtos;

import com.ubt.hospitalmanagement.AppointmentStatus;
import com.ubt.hospitalmanagement.entities.Slot;
import com.ubt.hospitalmanagement.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentDto {

    private Long id;

    private LocalDate date;

    private UserDto patient;

    private UserDto doctor;

    private String description;

    private SlotDto slot;

    private AppointmentStatus status;
}
