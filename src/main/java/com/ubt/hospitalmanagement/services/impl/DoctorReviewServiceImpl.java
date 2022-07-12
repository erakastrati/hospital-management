package com.ubt.hospitalmanagement.services.impl;

import com.ubt.hospitalmanagement.dtos.DoctorReviewDto;
import com.ubt.hospitalmanagement.dtos.requests.ReviewRequest;
import com.ubt.hospitalmanagement.dtos.response.mappers.DoctorReviewMapper;
import com.ubt.hospitalmanagement.entities.DoctorReview;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.repositories.DoctorReviewRepository;
import com.ubt.hospitalmanagement.services.DoctorReviewService;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorReviewServiceImpl implements DoctorReviewService {

    private final DoctorReviewRepository repository;
    private final UserService userService;

    public void addReview(ReviewRequest request){
        DoctorReview review = new DoctorReview();
        review.setReview(request.getReview());
        review.setDoctor(userService.getDoctorById(request.getDoctorId()));
        review.setPatient(userService.getCurrentUserDetails());

        repository.save(review);
    }

    public List<DoctorReviewDto> getDoctorReviews(Integer doctorId) {
        User doctor = userService.getDoctorById(doctorId);
        return DoctorReviewMapper.map(repository.findByDoctor(doctor));
    }

}
