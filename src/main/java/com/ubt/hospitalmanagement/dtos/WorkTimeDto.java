package com.ubt.hospitalmanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkTimeDto {

    private String day;
    // TODO - CHANGE VARIABLES TO ENGLISH ONES
    private boolean paradite;
    private boolean pasdite;
    private boolean pushim;
}
