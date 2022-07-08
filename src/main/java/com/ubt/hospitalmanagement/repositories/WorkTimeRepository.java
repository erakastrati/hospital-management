package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.entities.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {

    Optional<WorkTime> findFirstByDoctorAndDay(User doctor, String day);
    List<WorkTime> findByDoctorId(Integer id);

    void deleteByDoctor(User doctor);
}
