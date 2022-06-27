package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.AppointmentStatus;
import com.ubt.hospitalmanagement.entities.Appointment;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findFirstByDoctorAndDateAndSlot_Id(User doctor, LocalDate date, Long slotNumber);
    List<Appointment> findByDoctorAndDateAndStatusIsNot(User doctor, LocalDate date, AppointmentStatus status);
    Page<Appointment> findByDoctor(User doctor, Pageable pageable);
    List<Appointment> findByDoctor(User doctor);
    List<Appointment> findByPatient_IdOrderByDateDesc(Integer id);

}
