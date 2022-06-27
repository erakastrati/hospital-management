package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AvailableSlotsRequests;
import com.ubt.hospitalmanagement.services.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
@RequiredArgsConstructor
public class SlotsController {

    private final SlotService service;

    @PostMapping
    public ResponseEntity<List<SlotDto>> getAvailableSlotsPerDayAndDoctor(@RequestBody AvailableSlotsRequests request) {
        return ResponseEntity.ok(service.getAvailableSlotsPerDoctorAndDay(request));
    }

    @GetMapping("/appointment")
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(service.getDoctorAppointments());
    }

    @GetMapping("/appointment/all")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @PutMapping("/appointment/approve/{appointmentId}")
    public ResponseEntity<Void> approveAppointment(@PathVariable Long appointmentId) {
        service.approveAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/appointment/decline/{appointmentId}")
    public ResponseEntity<Void> declineAppointment(@PathVariable Long appointmentId) {
        service.declineAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }
}
