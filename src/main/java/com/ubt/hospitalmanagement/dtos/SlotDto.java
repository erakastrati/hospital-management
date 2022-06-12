package com.ubt.hospitalmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SlotDto {

    private Long id;
    private String timeSlot;
    private boolean paradite;
    private boolean pasdite;
    private Long slotNumber;
}
