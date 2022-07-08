package com.ubt.hospitalmanagement.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ScheduleRequest {

    private String weekDay;
    // TODO CHANGE IN ENGLISH ONES
    @JsonProperty
    private boolean paradite;

    @JsonProperty
    private boolean pasdite;

    @JsonProperty
    private boolean pushim;


}
