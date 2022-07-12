package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;

import java.util.List;

public interface DiagnoseService {

    public DiagnoseDto save(DiagnoseDto request);
    public List<DiagnoseDto> getDiagnosesForCurrentPatient();
    public List<DiagnoseDto> getDiagnosesForPatient(Integer patientId);
}
