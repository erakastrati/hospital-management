package com.ubt.hospitalmanagement.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {

    private int id;
    private String uuid;

    private String email;
    private boolean active;

    private List<String> experiences;
    private List<String> diseases;

    List<WorkTimeDto> workingDays;

    private String firstName;
    private String lastName;
}
