package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.UserDto;
import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/schedule")
    public void setDoctorSchedule(@RequestBody List<ScheduleRequest> request, @RequestParam String doctorUuid) {
        userService.setDoctorWorkingDays(request, doctorUuid);
    }

    @GetMapping("/doctors")
    public ResponseEntity<Page<UserDto>> getDoctors(Pageable pageable) {
        return ResponseEntity.ok(userService.getDoctors(pageable));
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUserdetails() {
        return ResponseEntity.ok(userService.getCurrentUserDetailsAsDto());
    }

    @GetMapping("/reports")
    public ResponseEntity<List<String>> getPatientHistory() {
        return ResponseEntity.ok(userService.getCurrentPatientReports());
    }

    // ONLY ADMIN ROLE
    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateUserFromAdmin(@PathVariable String uuid, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(uuid, userDto));
    }

}
