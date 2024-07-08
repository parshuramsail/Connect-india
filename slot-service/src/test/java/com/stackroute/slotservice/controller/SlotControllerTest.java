package com.stackroute.slotservice.controller;

import com.stackroute.slotservice.dto.SlotDto;
import com.stackroute.slotservice.service.SlotService;
import com.stackroute.slotservice.utils.SlotUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.stackroute.slotservice.utils.SlotUtils.getSlotJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SlotController.class)
public class SlotControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SlotService slotService;


/*    @Test
    public void getALLSlotTest() throws Exception {

       *//* when(slotService.fetchAllSlots()).thenReturn(SlotUtils.getSlots());
        mvc.perform(get("/api/v1/slot")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(SlotUtils.objectListJson()))
                        .andExpect(jsonPath("$.[*].slotId").isNotEmpty());
    }


    @Test
    public void postSlotTest() throws Exception {

        when(slotService.addSlot(any(SlotDto.class))).thenReturn(SlotUtils.getSlotDto());
        mvc.perform(post("/api/v1/slot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getSlotJson())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(content().json(getSlotJson()))
                        .andExpect(jsonPath("$.slotId").isNotEmpty());

    }*/
}
