package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.requests.DiagnoseDto;
import com.ubt.hospitalmanagement.entities.Diagnose;

import java.util.ArrayList;
import java.util.List;

public class DiagnoseMapper {

    public static DiagnoseDto map(Diagnose diagnose) {
        return DiagnoseDto.builder()
                .diagnose(diagnose.getDiagnose())
                .build();
    }

    public static List<DiagnoseDto> map(List<Diagnose> diagnoses) {
        List<DiagnoseDto> dtos = new ArrayList<>();
        for(Diagnose dia : diagnoses) {
            dtos.add(map(dia));
        }
        return dtos;
    }
}
