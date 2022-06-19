package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;
import com.ubt.hospitalmanagement.services.DiagnoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnose")
@RequiredArgsConstructor
public class DiagnoseController {

    private final DiagnoseService service;

    @PostMapping
    public ResponseEntity<DiagnoseDto> addDiagnoseToPatient(@RequestBody DiagnoseDto diagnose) {
        return ResponseEntity.ok(service.save(diagnose));
    }

    @GetMapping()
    public ResponseEntity<List<DiagnoseDto>> getPatientDiagnoses() {
        return ResponseEntity.ok(service.getDiagnosesForCurrentPatient());
    }

}
