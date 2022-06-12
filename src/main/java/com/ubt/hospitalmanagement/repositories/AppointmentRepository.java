package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findFirstByDoctorAndDateAndSlot_Id(User doctor, LocalDate date, Long slotNumber);
    List<Appointment> findByDoctorAndDate(User doctor, LocalDate date);

}
