package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.config.exceptions.UserNotFoundException;
import com.ubt.hospitalmanagement.dtos.*;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.dtos.requests.ContactUsDto;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.MyUserDetails;
import com.ubt.hospitalmanagement.dtos.response.mappers.UserMapper;
import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.dtos.requests.SignUpRequest;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.enums.Roles;
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
import java.time.LocalDate;
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
        createUser(User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .active(true)
                .roles(request.isDoctor() ? Roles.DOCTOR.name() : Roles.PATIENT.name())
                .dateOfBirth(request.getDateOfBirth())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .mobileNumber(request.getMobileNumber())
                .build());
    }

    public void addDoctor(DoctorDto doctorDto) {
        User doctor = UserMapper.map(doctorDto);
        createUser(doctor);
    }



    // TODO we need to update more properties
    public void updateDoctor(Integer doctorId, DoctorDto updateDoctorRequest) {
        User doctor = repository.findById(doctorId).orElseThrow(EntityNotFoundException::new);
        doctor.setExperiences(updateDoctorRequest.getExperiences());
        repository.save(doctor);
    }

    public void addPatient(PatientDto patientRequest) {
        User patient = UserMapper.map(patientRequest);
        createUser(patient);
    }

    public void addDissease(DisseaseDto request) {
        User user = repository.findById(request.getPatientId()).orElseThrow(EntityNotFoundException::new);
        user.getDiseases().add(request.getDissease());
        repository.save(user);
    }

    public void setDoctorWorkingDays(List<ScheduleRequest> request, Integer doctorId) {
        User doctor = getDoctorById(doctorId);
        List<WorkTime> workTimes = workTimeService.saveWorkTimes(request, doctor);

        doctor.setWorkingDays(workTimes);
        repository.save(doctor);
    }

    public Page<UserDto> getDoctors(Pageable pageable) {
        return repository.findByRolesContaining(Roles.DOCTOR.name(), pageable).map(UserMapper::map);
    }

    public Page<UserDto> getPatients(Pageable pageable) {
        Page<UserDto> users = repository.findByRolesContaining(Roles.PATIENT.name(), pageable).map(UserMapper::map);
        for(UserDto user : users.getContent()) {
            //set last appointment
            Appointment lastAppointment = appointmentService.getLastAppointmentOfPatient(user.getId());
            if(Optional.ofNullable(lastAppointment).isPresent()) {
                user.setLastAppointment(lastAppointment.getDate());
            }
        }
        return users;
    }

    private User createUser(User user) {
        Optional<User> optionalUser = repository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User already exists");
        }

//        user.setUuid(UUID.randomUUID().toString());
        repository.save(user);
        return user;
    }

    public User getDoctorById(Integer id) {
        return repository.findByIdAndRolesContaining(id, Roles.DOCTOR.name())
                .orElseThrow(EntityNotFoundException::new);
    }

    public User getPatientById(Integer id) {
        return repository.findByIdAndRolesContaining(id, Roles.PATIENT.name())
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserDto getPatientDtoById(Integer id) {
        return UserMapper.map(repository.findByIdAndRolesContaining(id, Roles.PATIENT.name())
                .orElseThrow(EntityNotFoundException::new));
    }

    public UserDto getDoctorDtoById(Integer id) {
        return UserMapper.map(repository.findByIdAndRolesContaining(id, Roles.DOCTOR.name())
                .orElseThrow(EntityNotFoundException::new));
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

    public UserDto getCurrentUserDetailsAsDto() {
        User user = getCurrentUserDetails();
        return UserMapper.map(user);
    }

    public List<String> getCurrentPatientReports() {
        User patient = getCurrentUserDetails();
        return patient.getDiseases();
    }

    public AppointmentDto setNewAppointment(AppointmentRequestDto request) throws UserNotFoundException, SlotNotValidException {
        User doctor = repository.findById(request.getDoctorId()).orElseThrow(UserNotFoundException::new);
        User currentPatient = getCurrentUserDetails();
        return appointmentService.save(request, doctor, currentPatient);
    }

    public Page<AppointmentDto> getCurrentDoctorAppointments(Pageable pageable) {
        User currentDoctor = getCurrentUserDetails();
        return appointmentService.getCurrentDoctorAppointments(currentDoctor, pageable);
    }

    private MyUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        return principal;
    }


//    public UserDto updateUser(String userUuid, UserDto userDto) {
//        User user = repository.findByUuid(userUuid).orElseThrow(EntityNotFoundException::new);
//
//        user.setDiseases(userDto.getDiseases());
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setMobileNumber(userDto.getMobileNumber());
//        user.setExperiences(userDto.getEmail());
//        return UserMapper.map(repository.save(user));
//    }

    public UserDto updateCurrentUser(UserDto userDto) {
        User user = getCurrentUserDetails();

        user.setDiseases(userDto.getDiseases());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setEmail(userDto.getEmail());
        user.setExperiences(userDto.getExperiences());
        return UserMapper.map(repository.save(user));
    }

    public void contactUs(ContactUsDto request) {
        // send email to us -TBD
    }

    public List<UserDto> getDoctors() {
        return UserMapper.map(repository.findByRolesContaining(Roles.DOCTOR.name()));
    }
}
