package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.Posts;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findByOwner(User owner, Pageable pageable);
}
