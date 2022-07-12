package com.ubt.hospitalmanagement.services;

import com.ubt.hospitalmanagement.dtos.DoctorReviewDto;
import com.ubt.hospitalmanagement.dtos.requests.ReviewRequest;

import java.util.List;

public interface DoctorReviewService {

    public void addReview(ReviewRequest request);
    public List<DoctorReviewDto> getDoctorReviews(Integer doctorId);
}
