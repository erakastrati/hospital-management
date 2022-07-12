package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;
import com.ubt.hospitalmanagement.services.impl.DiagnoseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/diagnose")
@RequiredArgsConstructor
public class DiagnoseController {

    private final DiagnoseServiceImpl service;

    @PostMapping
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN"})
    public ResponseEntity<DiagnoseDto> addDiagnoseToPatient(@RequestBody DiagnoseDto diagnose) {
        return ResponseEntity.ok(service.save(diagnose));
    }

    @GetMapping()
    @RolesAllowed({"ROLE_PATIENT"})
    public ResponseEntity<List<DiagnoseDto>> getPatientDiagnoses() {
        return ResponseEntity.ok(service.getDiagnosesForCurrentPatient());
    }

    @GetMapping("/patient/{patientId}")
    @RolesAllowed({"ROLE_DOCTOR", "ROLE_ADMIN"})
    public ResponseEntity<List<DiagnoseDto>> getPatientDiagnoses(@PathVariable("patientId") Integer patientId) {
        return ResponseEntity.ok(service.getDiagnosesForPatient(patientId));
    }

}
