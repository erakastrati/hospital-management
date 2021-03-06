package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findByParadite(boolean paradite);
}
