package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.DoctorReviewDto;
import com.ubt.hospitalmanagement.dtos.requests.ReviewRequest;
import com.ubt.hospitalmanagement.services.DoctorReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class DoctorReviewController {

    private final DoctorReviewService service;

    @PostMapping
    public void addReview(@RequestBody ReviewRequest request){
        service.addReview(request);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<List<DoctorReviewDto>> getSpecificDoctorReviews(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(service.getDoctorReviews(doctorId));
    }
}
