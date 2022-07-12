package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AvailableSlotsRequests;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SlotService {

    public List<SlotDto> getAvailableSlotsPerDoctorAndDay(AvailableSlotsRequests requests);
    public List<AppointmentDto> getDoctorAppointments();
    public List<AppointmentDto> getAllAppointments();
    public void approveAppointment(Long id);
    public void declineAppointment(Long id);

}
