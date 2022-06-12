package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.WorkTimeDto;
import com.ubt.hospitalmanagement.entities.WorkTime;

import java.util.ArrayList;
import java.util.List;

public class WorkTimeMapper {

    public static List<WorkTimeDto> map(List<WorkTime> workTimes) {
        List<WorkTimeDto> workingTimes = new ArrayList<>();
        for(WorkTime workTime : workTimes) {
            workingTimes.add(map(workTime));
        }
        return workingTimes;
    }

    public static WorkTimeDto map(WorkTime workTime) {
        return WorkTimeDto.builder()
                .day(workTime.getDay())
                .paradite(workTime.isParadite())
                .pasdite(workTime.isPasdite())
                .pushim(workTime.isPushim())
                .build();
    }
}
