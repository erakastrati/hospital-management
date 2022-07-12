package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.config.exceptions.SlotNotValidException;
import com.ubt.hospitalmanagement.config.exceptions.UserNotFoundException;
import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.UserDto;
import com.ubt.hospitalmanagement.dtos.requests.AppointmentRequestDto;
import com.ubt.hospitalmanagement.dtos.requests.ContactUsDto;
import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/schedule")
    @RolesAllowed({"ROLE_ADMIN"})
    public void setDoctorSchedule(@RequestBody List<ScheduleRequest> request, @RequestParam Integer id) {
        userService.setDoctorWorkingDays(request, id);
    }

    @GetMapping("/schedule/{doctorId}")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<List<ScheduleRequest>> getDoctorSchedule(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(userService.getDoctorSchedule(doctorId));
    }

    @GetMapping("/doctors")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<Page<UserDto>> getDoctors(Pageable pageable) {
        return ResponseEntity.ok(userService.getDoctors(pageable));
    }

    @GetMapping("/patients")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getPatients(Pageable pageable) {
        return ResponseEntity.ok(userService.getPatients(pageable));
    }

    @GetMapping("/reports")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN"})
    public ResponseEntity<List<String>> getPatientHistory() {
        return ResponseEntity.ok(userService.getCurrentPatientReports());
    }

    @PostMapping("/profile-picture")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam("pic") MultipartFile picture) throws IOException {
        this.userService.uploadProfilePicture(picture);
        return ResponseEntity.ok().build();
    }

//    // ONLY ADMIN ROLE
//    @PutMapping("/{uuid}")
//    public ResponseEntity<?> updateUserFromAdmin(@PathVariable String uuid, @RequestBody UserDto userDto) {
//        return ResponseEntity.ok(userService.updateUser(uuid, userDto));
//    }

    @PutMapping()
    @RolesAllowed({"ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_PATIENT"})
    public ResponseEntity<?> updateCurrentUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateCurrentUser(userDto));
    }

    @PostMapping("/appointment")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<AppointmentDto> setNewAppointment(@RequestBody AppointmentRequestDto requestDto) throws UserNotFoundException, SlotNotValidException {
        return ResponseEntity.ok(userService.setNewAppointment(requestDto));
    }

    @GetMapping("/appointment")
    @RolesAllowed({"ROLE_DOCTOR"})
    public ResponseEntity<Page<AppointmentDto>> getCurrentDoctorAppointments(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.getCurrentDoctorAppointments(pageable));
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> contactUs(@RequestBody ContactUsDto contactRequest) {
        userService.contactUs(contactRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/doctor-details/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<UserDto> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getDoctorDtoById(id));
    }

    @GetMapping("/patient-details/{id}")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN"})
    public ResponseEntity<UserDto> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getPatientDtoById(id));
    }

    @GetMapping
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN", "ROLE_PATIENT"})
    public ResponseEntity<UserDto> getCurrentUserDetails() {
        return ResponseEntity.ok(userService.getCurrentUserDetailsAsDto());
    }

}
