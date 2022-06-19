package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.DoctorReviewDto;
import com.ubt.hospitalmanagement.entities.DoctorReview;

import java.util.ArrayList;
import java.util.List;

public class DoctorReviewMapper {

    public static DoctorReviewDto map(DoctorReview review) {
        return DoctorReviewDto.builder()
                .review(review.getReview())
                .build();
    }

    public static List<DoctorReviewDto> map(List<DoctorReview> reviews) {
        List<DoctorReviewDto> dtos = new ArrayList<>();
        for(DoctorReview review : reviews) {
            dtos.add(map(review));
        }
        return dtos;
    }
}
