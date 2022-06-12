package com.ubt.hospitalmanagement.mappers;

import com.ubt.hospitalmanagement.dtos.SlotDto;
import com.ubt.hospitalmanagement.entities.Slot;

import java.util.ArrayList;
import java.util.List;

public class SlotMapper {

    public static SlotDto map(Slot slot) {
        return SlotDto.builder()
                .id(slot.getId())
                .paradite(slot.isParadite())
                .pasdite(slot.isPasdite())
                .slotNumber(slot.getSlotNumber())
                .timeSlot(slot.getTimeSlot())
                .build();
    }

    public static List<SlotDto> map(List<Slot> slots) {
        List<SlotDto> slotsMapped = new ArrayList<>();
        for(Slot slot : slots) {
            slotsMapped.add(map(slot));
        }
        return slotsMapped;
    }
}
