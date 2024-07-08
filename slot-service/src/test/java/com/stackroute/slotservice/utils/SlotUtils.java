package com.stackroute.slotservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.slotservice.dto.SlotDto;
import com.stackroute.slotservice.modal.Slot;
import com.stackroute.slotservice.enums.Status;
import com.stackroute.slotservice.enums.TimeAvailability;

import java.util.ArrayList;
import java.util.List;

public class SlotUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static SlotDto getSlotDto(){
        SlotDto slotDto=new SlotDto();
        slotDto.setSlotId(1);
        slotDto.setDate("12-10-2022");
        slotDto.setEndTime("08:29");
        slotDto.setStartTime("08:34");
        slotDto.setTimeAvailability(TimeAvailability.MORNING);
        slotDto.setAvailability(Status.AVAILABILITY);
        return slotDto;
    }

    public static Slot getSlot(){
        Slot slot=new Slot();
        slot.setSlotId(1);
        slot.setDate("12-10-2022");
        slot.setEndTime("08:29");
        slot.setStartTime("08:34");
        slot.setTimeAvailability(TimeAvailability.MORNING);
        slot.setAvailability(Status.AVAILABILITY);
        return slot;
    }

    public static List< SlotDto> getSlots() {
        List<SlotDto>  slotDtoList = new ArrayList<>();
        slotDtoList.add(getSlotDto());
        return slotDtoList;
    }

    public static String objectListJson() {

        final SlotDto slotDto = SlotUtils.getSlotDto();
//        final SlotDto slotDto1 = SlotUtils.getSlotsDto();

        try{
            return objectMapper.writeValueAsString(List.of(slotDto));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getSlotJson() {

        final SlotDto slotDto = SlotUtils.getSlotDto();
        try{
            return objectMapper.writeValueAsString(slotDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}