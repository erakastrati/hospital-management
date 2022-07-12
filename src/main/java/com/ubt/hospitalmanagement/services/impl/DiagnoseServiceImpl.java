package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;
import com.ubt.hospitalmanagement.dtos.response.mappers.DiagnoseMapper;
import com.ubt.hospitalmanagement.entities.Diagnose;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.repositories.DiagnoseRepository;
import com.ubt.hospitalmanagement.services.DiagnoseService;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiagnoseServiceImpl implements DiagnoseService {

    private final DiagnoseRepository repository;
    private final UserService userService;

    public DiagnoseDto save(DiagnoseDto request) {
        User patient = userService.getPatientById(request.getPatientId());
        User currentDoctor = userService.getCurrentUserDetails();

        Diagnose diagnose = Diagnose.builder()
                .diagnose(request.getDiagnose())
                .patient(patient)
                .doctor(currentDoctor)
                .build();

        return DiagnoseMapper.map(repository.save(diagnose));
    }

    public List<DiagnoseDto> getDiagnosesForCurrentPatient() {
        User patient = userService.getCurrentUserDetails();
        return DiagnoseMapper.map(repository.findByPatient(patient));
    }

    public List<DiagnoseDto> getDiagnosesForPatient(Integer patientId) {
        User patient = userService.getPatientById(patientId);
        return DiagnoseMapper.map(repository.findByPatient(patient));
    }

}
