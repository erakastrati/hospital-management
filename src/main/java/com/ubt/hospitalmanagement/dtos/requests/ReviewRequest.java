package com.ubt.hospitalmanagement.dtos.requests;

import lombok.Data;

@Data

public class ReviewRequest {

    private String review;
    private String doctorUuid;

}
