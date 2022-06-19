package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.DoctorReview;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorReviewRepository extends JpaRepository<DoctorReview, Long> {

    List<DoctorReview> findByDoctor(User doctor);
}
