package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
}
