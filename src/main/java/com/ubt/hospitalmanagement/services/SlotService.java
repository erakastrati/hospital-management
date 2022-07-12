package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.AppointmentDto;
import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.dtos.requests.AvailableSlotsRequests;

import java.util.List;

public interface SlotService {

    public List<SlotDto> getAvailableSlotsPerDoctorAndDay(AvailableSlotsRequests requests);
    public List<AppointmentDto> getDoctorAppointments();
    public List<AppointmentDto> getAllAppointments();
    public void approveAppointment(Long id);
    public void declineAppointment(Long id);

}
