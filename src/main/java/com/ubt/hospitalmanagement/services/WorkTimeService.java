package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.requests.ScheduleRequest;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;

import java.util.List;

public interface WorkTimeService {

    public List<WorkTime> saveWorkTimes(List<ScheduleRequest> requests, User doctor);
    public WorkTime getWorkTimeForDoctorAndWeekDay(User doctor, String weekDay);
    public List<WorkTime> getScheduleForDoctor(Integer doctorId);
}
