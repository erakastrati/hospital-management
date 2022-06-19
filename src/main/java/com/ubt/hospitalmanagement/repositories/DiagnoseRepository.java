package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.Diagnose;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

    List<Diagnose> findByPatient(User patient);
}
