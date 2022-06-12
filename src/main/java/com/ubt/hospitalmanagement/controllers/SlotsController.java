package com.ubt.hospitalmanagement.controllers;

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
}
