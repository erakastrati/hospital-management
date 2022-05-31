package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.DoctorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorReviewRepository extends JpaRepository<DoctorReview, Long> {
}
