package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.enums.AppointmentStatus;
import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AvailableSlotsRequests;
import com.ubt.hospitalmanagement.dtos.response.mappers.AppointmentMapper;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.Slot;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.dtos.response.mappers.SlotMapper;
import com.ubt.hospitalmanagement.repositories.AppointmentRepository;
import com.ubt.hospitalmanagement.repositories.SlotRepository;
import com.ubt.hospitalmanagement.services.SlotService;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SlotServiceImpl implements SlotService {

    private final SlotRepository repository;
    private final WorkTimeServiceImpl workTimeServiceImpl;
    private final UserService userService;
    private final AppointmentRepository appointmentRepository;

    public List<SlotDto> getAvailableSlotsPerDoctorAndDay(AvailableSlotsRequests requests) {
        User doctor = userService.getDoctorById(requests.getDoctorId());
        WorkTime workTime = workTimeServiceImpl.getWorkTimeForDoctorAndWeekDay(doctor, requests.getDate().getDayOfWeek().name().toLowerCase());
        List<Slot> allSlots = new ArrayList<>();
        if(workTime.isParadite()) {
            allSlots = repository.findByParadite(true);
        } else {
            allSlots = repository.findByParadite(false);
        }

        List<Slot> busySlots = appointmentRepository.findByDoctorAndDateAndStatusIsNot(doctor, requests.getDate(), AppointmentStatus.DECLINED)
                .stream()
                .map(Appointment::getSlot)
                .collect(Collectors.toList());

        return SlotMapper.map(allSlots.stream().filter(slot -> !busySlots.contains(slot)).collect(Collectors.toList()));
    }

    public List<AppointmentDto> getDoctorAppointments() {
        User currentDoctor = userService.getCurrentUserDetails();
        return AppointmentMapper.map(appointmentRepository.findByDoctor(currentDoctor));
    }

    public List<AppointmentDto> getAllAppointments() {
        return AppointmentMapper.map(appointmentRepository.findAll());
    }

    public void approveAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        appointment.setStatus(AppointmentStatus.APPROVED);
        appointmentRepository.save(appointment);
    }

    public void declineAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        appointment.setStatus(AppointmentStatus.DECLINED);
        appointmentRepository.save(appointment);
    }
}
