package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.entities.Appointment;

import java.util.Optional;

public class AppointmentMapper {

    public static AppointmentDto map(Appointment appointment) {
        return AppointmentDto.builder()
                .slot(Optional.ofNullable(appointment.getSlot()).map(SlotMapper::map).orElse(null))
                .date(appointment.getDate())
                .description(appointment.getDescription())
                .id(appointment.getId())
                .doctor(Optional.ofNullable(appointment.getDoctor()).map(UserMapper::map).orElse(null))
                .patient(Optional.ofNullable(appointment.getPatient()).map(UserMapper::map).orElse(null))
                .build();
    }
}
