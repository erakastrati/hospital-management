package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.enums.AppointmentStatus;
import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.Slot;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.dtos.response.mappers.AppointmentMapper;
import com.ubt.hospitalmanagement.repositories.AppointmentRepository;
import com.ubt.hospitalmanagement.repositories.SlotRepository;
import com.ubt.hospitalmanagement.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final SlotRepository slotRepository;
    private final WorkTimeServiceImpl workTimeServiceImpl;

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
        WorkTime workTime = workTimeServiceImpl.getWorkTimeForDoctorAndWeekDay(doctor, DAY_OF_WEEK);
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
                .status(AppointmentStatus.PENDING_APPROVAL)
                .build()));

     }

     public Page<AppointmentDto> getCurrentDoctorAppointments(User doctor, Pageable pageable) {
        return repository.findByDoctor(doctor, pageable).map(AppointmentMapper::map);
     }

     public Appointment getLastAppointmentOfPatient(Integer patientId) {
        List<Appointment> appointments = repository.findByPatient_IdOrderByDateDesc(patientId);
        if(!appointments.isEmpty()) {
            return appointments.stream().findFirst().get();
        }
        return null;
     }


}
