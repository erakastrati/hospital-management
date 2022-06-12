package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.config.exceptions.UserNotFoundException;
import com.ubt.hospitalmanagement.dtos.*;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.entities.MyUserDetails;
import com.ubt.hospitalmanagement.mappers.UserMapper;
import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.dtos.requests.SignUpRequest;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.enums.Roles;
import com.ubt.hospitalmanagement.repositories.AppointmentRepository;
import com.ubt.hospitalmanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final WorkTimeService workTimeService;

    private final AppointmentService appointmentService;

    public void createUser(SignUpRequest request)  {
        createUser(User.builder().email(request.getEmail()).password(request.getPassword()).build());
    }

    public void addDoctor(DoctorDto doctorDto) {
        User doctor = UserMapper.map(doctorDto);
        createUser(doctor);
    }



    // TODO we need to update more properties
    public void updateDoctor(String doctorUuid, DoctorDto updateDoctorRequest) {
        User doctor = repository.findByUuid(doctorUuid).orElseThrow(EntityNotFoundException::new);
        doctor.setExperiences(updateDoctorRequest.getExperiences());
        repository.save(doctor);
    }

    public void addPatient(PatientDto patientRequest) {
        User patient = UserMapper.map(patientRequest);
        createUser(patient);
    }

    public void addDissease(DisseaseDto request) {
        User user = repository.findByUuid(request.getPatientUuid()).orElseThrow(EntityNotFoundException::new);
        user.getDiseases().add(request.getDissease());
        repository.save(user);
    }

    public void setDoctorWorkingDays(List<ScheduleRequest> request, String doctorUuid) {
        User doctor = getDoctorByUUID(doctorUuid);
        List<WorkTime> workTimes = workTimeService.saveWorkTimes(request, doctor);

        doctor.setWorkingDays(workTimes);
        repository.save(doctor);
    }

    public Page<UserDto> getDoctors(Pageable pageable) {
        return repository.findByRolesContaining(Roles.DOCTOR.name(), pageable).map(UserMapper::map);
    }

    private User createUser(User user) {
        Optional<User> optionalUser = repository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User already exists");
        }

        user.setUuid(UUID.randomUUID().toString());
        repository.save(user);
        return user;
    }

    public User getDoctorByUUID(String uuid) {
        return repository.findByUuidAndRolesContaining(uuid, Roles.DOCTOR.name())
                .orElseThrow(EntityNotFoundException::new);
    }

    public User getCurrentUserDetails() {
        MyUserDetails principal = getCurrentUser();
        User currentUser = null;
        if(principal != null) {
            Optional<User> optionalUser = repository.findByEmail(principal.getUsername());
            if(optionalUser.isPresent())
                currentUser = optionalUser.get();
        }
        return currentUser;
    }

    public List<String> getCurrentPatientReports() {
        User patient = getCurrentUserDetails();
        return patient.getDiseases();
    }

    public AppointmentDto setNewAppointment(AppointmentRequestDto request) throws UserNotFoundException, SlotNotValidException {
        User doctor = repository.findByUuid(request.getDoctorUuid()).orElseThrow(UserNotFoundException::new);
        User currentPatient = getCurrentUserDetails();
        return appointmentService.save(request, doctor, currentPatient);
    }

    private MyUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        return principal;
    }

    //update patient
    public UserDto updateUser(String userUuid, UserDto userDto) {
        User user = repository.findByUuid(userUuid).orElseThrow(EntityNotFoundException::new);
        // update patient
        user.setDiseases(userDto.getDiseases());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return UserMapper.map(repository.save(user));
    }
}
