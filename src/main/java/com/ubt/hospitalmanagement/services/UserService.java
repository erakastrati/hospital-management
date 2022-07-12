package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.config.exceptions.UserNotFoundException;
import com.ubt.hospitalmanagement.dtos.*;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.dtos.requests.ContactUsDto;
import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.dtos.requests.SignUpRequest;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {

    public void createUser(SignUpRequest request);
    public void addDoctor(DoctorDto doctorDto);
    public void updateDoctor(Integer doctorId, DoctorDto updateDoctorRequest);
    public void addPatient(PatientDto patientRequest);
    public void addDissease(DisseaseDto request);
    public void setDoctorWorkingDays(List<ScheduleRequest> request, Integer doctorId);
    public Page<UserDto> getDoctors(Pageable pageable);
    public Page<UserDto> getPatients(Pageable pageable);
    public User getDoctorById(Integer id);
    public User getPatientById(Integer id);
    public UserDto getPatientDtoById(Integer id);
    public UserDto getDoctorDtoById(Integer id);
    public User getCurrentUserDetails();
    public UserDto getCurrentUserDetailsAsDto();
    public List<String> getCurrentPatientReports();
    public AppointmentDto setNewAppointment(AppointmentRequestDto request) throws UserNotFoundException, SlotNotValidException;
    public Page<AppointmentDto> getCurrentDoctorAppointments(Pageable pageable);
    public UserDto updateCurrentUser(UserDto userDto);
    public void contactUs(ContactUsDto request);
    public List<UserDto> getDoctors();
    public void uploadProfilePicture(MultipartFile picture) throws IOException;

    public List<ScheduleRequest> getDoctorSchedule(Integer doctorId);
}
