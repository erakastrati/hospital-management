package com.ubt.hospitalmanagement.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ScheduleRequest {

    private String doctorUuid;
    private String weekDay;
    // TODO CHANGE IN ENGLISH ONES
    @JsonProperty
    private boolean paradite;

    @JsonProperty
    private boolean pasdite;

    @JsonProperty
    private boolean pushim;


}
