package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.DoctorReviewDto;
import com.ubt.hospitalmanagement.dtos.requests.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorReviewService {

    public void addReview(ReviewRequest request);
    public List<DoctorReviewDto> getDoctorReviews(Integer doctorId);
}
