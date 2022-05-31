package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.requests.ReviewRequest;
import com.ubt.hospitalmanagement.services.DoctorReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class DoctorReviewController {

    private final DoctorReviewService service;

    @PostMapping
    public void addReview(@RequestBody ReviewRequest request){
        service.addReview(request);
    }
}
