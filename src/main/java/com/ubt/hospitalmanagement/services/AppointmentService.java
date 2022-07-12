package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService {

    public AppointmentDto save(AppointmentRequestDto requestDto, User doctor, User patient) throws SlotNotValidException;
    public Page<AppointmentDto> getCurrentDoctorAppointments(User doctor, Pageable pageable);
    public Appointment getLastAppointmentOfPatient(Integer patientId);
}
