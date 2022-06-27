package com.ubt.hospitalmanagement.repositories;

import com.ubt.hospitalmanagement.entities.Posts;
import com.ubt.hospitalmanagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends MongoRepository<Posts, String> {

    Page<Posts> findByDoctorId(Integer doctorId, Pageable pageable);
}
