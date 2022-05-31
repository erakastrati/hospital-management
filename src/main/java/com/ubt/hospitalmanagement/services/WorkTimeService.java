package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.repositories.WorkTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkTimeService {

    private final WorkTimeRepository repository;

    public List<WorkTime> saveWorkTimes(List<ScheduleRequest> requests) {
        List<WorkTime> workTimes = new ArrayList<>();
        for(ScheduleRequest request : requests) {
            workTimes.add(repository.save(WorkTime.builder()
                            .day(request.getWeekDay())
                            .paradite(request.isParadite())
                            .pasdite(request.isPasdite())
                    .build())
            );
        }
        return workTimes;
    }
}
