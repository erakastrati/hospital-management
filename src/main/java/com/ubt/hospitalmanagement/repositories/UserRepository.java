package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userName);

    Optional<User> findByIdAndRolesContaining(Integer id, String roles);

    Page<User> findByRolesContaining(String role, Pageable pageable);
    List<User> findByRolesContaining(String role);
}
