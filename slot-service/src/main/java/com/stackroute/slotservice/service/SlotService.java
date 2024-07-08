package com.stackroute.slotservice.service;

import com.stackroute.slotservice.dto.CustomerOrderDto;
import com.stackroute.slotservice.dto.SlotDto;

import java.util.List;

public interface SlotService {
     SlotDto addSlot( SlotDto slotDto);
      void getorderdetails(CustomerOrderDto customerOrderDto);

    List<SlotDto> fetchAllSlots();

    SlotDto fetchSlotById(Long slotId);

   SlotDto updateSlotById(Long slotId,SlotDto slotDto);

    void deleteSlotById(Long slotId);


}