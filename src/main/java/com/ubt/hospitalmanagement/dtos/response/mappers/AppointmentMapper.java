package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.entities.Appointment;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentMapper {

    public static AppointmentDto map(Appointment appointment) {
        return AppointmentDto.builder()
                .slot(Optional.ofNullable(appointment.getSlot()).map(SlotMapper::map).orElse(null))
                .date(appointment.getDate())
                .description(appointment.getDescription())
                .id(appointment.getId())
//                .doctor(Optional.ofNullable(appointment.getDoctor()).map(UserMapper::map).orElse(null))
//                .patient(Optional.ofNullable(appointment.getPatient()).map(UserMapper::map).orElse(null))
                .status(appointment.getStatus())
                .build();
    }

    public static List<AppointmentDto> map(List<Appointment> appointments) {
        List<AppointmentDto> dtos = new ArrayList<>();
        for(Appointment app : appointments) {
            dtos.add(map(app));
        }
        return dtos;
    }

}
