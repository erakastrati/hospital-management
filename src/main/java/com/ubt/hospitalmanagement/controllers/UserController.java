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

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/schedule")
    public void setDoctorSchedule(@RequestBody List<ScheduleRequest> request, @RequestParam Integer id) {
        userService.setDoctorWorkingDays(request, id);
    }

    @GetMapping("/doctors")
    public ResponseEntity<Page<UserDto>> getDoctors(Pageable pageable) {
        return ResponseEntity.ok(userService.getDoctors(pageable));
    }

    @GetMapping("/patients")
    public ResponseEntity<Page<UserDto>> getPatients(Pageable pageable) {
        return ResponseEntity.ok(userService.getPatients(pageable));
    }

    @GetMapping("/reports")
    public ResponseEntity<List<String>> getPatientHistory() {
        return ResponseEntity.ok(userService.getCurrentPatientReports());
    }

//    // ONLY ADMIN ROLE
//    @PutMapping("/{uuid}")
//    public ResponseEntity<?> updateUserFromAdmin(@PathVariable String uuid, @RequestBody UserDto userDto) {
//        return ResponseEntity.ok(userService.updateUser(uuid, userDto));
//    }

    @PutMapping()
    public ResponseEntity<?> updateUserFromAdmin(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateCurrentUser(userDto));
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDto> setNewAppointment(@RequestBody AppointmentRequestDto requestDto) throws UserNotFoundException, SlotNotValidException {
        return ResponseEntity.ok(userService.setNewAppointment(requestDto));
    }

    @GetMapping("/appointment")
    public ResponseEntity<Page<AppointmentDto>> getCurrentDoctorAppointments(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.getCurrentDoctorAppointments(pageable));
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> contactUs(@RequestBody ContactUsDto contactRequest) {
        userService.contactUs(contactRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/doctor-details/{id}")
    public ResponseEntity<UserDto> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getDoctorDtoById(id));
    }

    @GetMapping("/patient-details/{id}")
    public ResponseEntity<UserDto> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getPatientDtoById(id));
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUserDetails() {
        return ResponseEntity.ok(userService.getCurrentUserDetailsAsDto());
    }

}
