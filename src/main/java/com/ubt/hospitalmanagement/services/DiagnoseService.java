package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagnoseService {

    public DiagnoseDto save(DiagnoseDto request);
    public List<DiagnoseDto> getDiagnosesForCurrentPatient();
    public List<DiagnoseDto> getDiagnosesForPatient(Integer patientId);
}
