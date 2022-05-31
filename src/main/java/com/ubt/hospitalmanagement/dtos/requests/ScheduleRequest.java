package com.ubt.hospitalmanagement.dtos.requests;

import lombok.Data;

@Data
public class ScheduleRequest {

    private String doctorUuid;
    private String weekDay;
    // TODO CHANGE IN ENGLISH ONES
    private boolean paradite;
    private boolean pasdite;
    private boolean pushim;
}
