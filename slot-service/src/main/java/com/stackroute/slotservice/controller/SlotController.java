package com.stackroute.slotservice.controller;


import com.stackroute.slotservice.dto.SlotDto;
import com.stackroute.slotservice.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("api/v1")
public class SlotController {
    @Autowired
    private SlotService slotService;
    @Value("${delete.message}")
    private String deletionMessage;


    @PostMapping("/slot")
    public ResponseEntity<SlotDto> addSlot(@RequestBody SlotDto slotDto) {

        return new ResponseEntity<SlotDto>(slotService.addSlot(slotDto), HttpStatus.CREATED);
    }

    @GetMapping("/slot")
    public ResponseEntity<List<SlotDto>> getAllSlots() {
        List<SlotDto> slotDtoList = slotService.fetchAllSlots();
        return new ResponseEntity<>(slotDtoList, HttpStatus.OK);
    }


    @GetMapping("/slot/{slotId}")
    public ResponseEntity<SlotDto> getSlotById(@PathVariable Long slotId) {
        return new ResponseEntity<SlotDto>(slotService.fetchSlotById(slotId), HttpStatus.OK);
    }

    @PutMapping("/slot/{slotId}")
    public ResponseEntity<SlotDto> updateSlotById(@PathVariable Long slotId, @RequestBody SlotDto slotDto) {
        return new ResponseEntity<SlotDto>(slotService.updateSlotById(slotId, slotDto), HttpStatus.OK);
    }

    @DeleteMapping("/slot/{slotId}")
    public ResponseEntity<String> deleteSlotById(@PathVariable Long slotId) {
        slotService.deleteSlotById(slotId);
        return new ResponseEntity<String>(deletionMessage, HttpStatus.OK);
    }
}
