package com.ubt.hospitalmanagement.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {

    private String email;
    private String password;
}
