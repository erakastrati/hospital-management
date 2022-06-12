package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.Slot;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.mappers.AppointmentMapper;
import com.ubt.hospitalmanagement.repositories.AppointmentRepository;
import com.ubt.hospitalmanagement.repositories.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final SlotRepository slotRepository;
    private final WorkTimeService workTimeService;

    public AppointmentDto save(AppointmentRequestDto requestDto, User doctor, User patient) throws SlotNotValidException {

        Optional<Appointment> optional = repository.findFirstByDoctorAndDateAndSlot_Id(
                doctor,
                requestDto.getDate(),
                requestDto.getSlot().getSlotNumber()
        );

        if(optional.isPresent()) {
            throw new EntityExistsException("An appointment already exists");
        }

        final String DAY_OF_WEEK = requestDto.getDate().getDayOfWeek().name().toLowerCase();

        //check ne qet dite qysh e ka orarin, para ose pas dites
        WorkTime workTime = workTimeService.getWorkTimeForDoctorAndWeekDay(doctor, DAY_OF_WEEK);
        List<Slot> slots = new ArrayList<>();

        if(workTime.isPushim()) {
            throw new SlotNotValidException("Sloti nuk osht valid");
        }
        if(workTime.isParadite()) {
            slots = slotRepository.findByParadite(true);
        } else {
            slots = slotRepository.findByParadite(false);
        }

        //masi e kemi ba check se a punon ky para a masdite, duhet me kqyr a na ka ardh requesti per paradite a masdite

        Slot foundAvailableSlot = slots.stream().filter(sl -> sl.getSlotNumber().equals(requestDto.getSlot().getSlotNumber())).findFirst().orElseThrow(() -> new SlotNotValidException("Sloti nuk osht valid"));

        return AppointmentMapper.map(repository.save(Appointment.builder()
                .description(requestDto.getDescription())
                .doctor(doctor)
                .patient(patient)
                .date(requestDto.getDate())
                .slot(foundAvailableSlot)
                .build()));

     }


}
