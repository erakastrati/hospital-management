package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import com.ubt.hospitalmanagement.repositories.WorkTimeRepository;
import com.ubt.hospitalmanagement.services.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    private final WorkTimeRepository repository;

    @Transactional
    public List<WorkTime> saveWorkTimes(List<ScheduleRequest> requests, User doctor) {
        List<WorkTime> workTimes = new ArrayList<>();
        repository.deleteByDoctor(doctor);
        for(ScheduleRequest request : requests) {
            workTimes.add(repository.save(WorkTime.builder()
                            .day(request.getWeekDay())
                            .paradite(request.isParadite())
                            .pasdite(request.isPasdite())
                            .pushim(request.isPushim())
                            .doctor(doctor)
                    .build())
            );
        }
        return workTimes;
    }

    public WorkTime getWorkTimeForDoctorAndWeekDay(User doctor, String weekDay) {
        WorkTime workTime = repository.findFirstByDoctorAndDay(doctor, weekDay.toLowerCase())
                                      .orElseThrow(EntityNotFoundException::new);
        return workTime;
    }

    public List<WorkTime> getScheduleForDoctor(Integer doctorId) {
        return repository.findByDoctorId(doctorId);
    }
}
