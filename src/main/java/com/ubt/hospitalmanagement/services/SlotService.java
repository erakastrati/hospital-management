package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AvailableSlotsRequests;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.Slot;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.mappers.SlotMapper;
import com.ubt.hospitalmanagement.repositories.AppointmentRepository;
import com.ubt.hospitalmanagement.repositories.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SlotService {

    private final SlotRepository repository;
    private final WorkTimeService workTimeService;
    private final UserService userService;
    private final AppointmentRepository appointmentRepository;

    public List<SlotDto> getAvailableSlotsPerDoctorAndDay(AvailableSlotsRequests requests) {
        User doctor = userService.getDoctorByUUID(requests.getDoctorUuid());
        WorkTime workTime = workTimeService.getWorkTimeForDoctorAndWeekDay(doctor, requests.getDate().getDayOfWeek().name().toLowerCase());
        List<Slot> allSlots = new ArrayList<>();
        if(workTime.isParadite()) {
            allSlots = repository.findByParadite(true);
        } else {
            allSlots = repository.findByParadite(false);
        }

        List<Slot> busySlots = appointmentRepository.findByDoctorAndDate(doctor, requests.getDate()).stream()
                .map(Appointment::getSlot)
                .collect(Collectors.toList());

        return SlotMapper.map(allSlots.stream().filter(slot -> !busySlots.contains(slot)).collect(Collectors.toList()));
    }
}
