package com.stackroute.slotservice.service;

import com.stackroute.slotservice.dto.SlotDto;
import com.stackroute.slotservice.exception.IDNotFoundException;
import com.stackroute.slotservice.modal.Slot;
import com.stackroute.slotservice.repository.SlotRepository;
import com.stackroute.slotservice.utils.SlotUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SlotServiceImplTest {

    @InjectMocks
    SlotServiceImpl  slotService;

    @Mock
    SlotRepository slotRepository;

    @Mock
    SequenceGeneratorService sequenceGeneratorService;


    @Test
    public void addSlotTest () {
        when(slotRepository.save(any(Slot.class))).thenReturn(SlotUtils.getSlot());
        when(slotRepository.findById(1l)).thenReturn(Optional.of(SlotUtils.getSlot()));
        when(sequenceGeneratorService.generateSequence(any())).thenReturn(1L);
        SlotDto slotDto = SlotUtils.getSlotDto();
      /*  SlotDto actualResponse = slotService.addSlot(slotDto);*/
      /*  Assertions.assertEquals(slotDto.toString(),actualResponse.toString());
        Assertions.assertEquals(1L,actualResponse.getSlotId());*/
    }



}
