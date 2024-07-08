package com.stackroute.slotservice.dto;


import com.stackroute.slotservice.enums.Status;
import com.stackroute.slotservice.enums.TimeAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SlotDto {
    private long slotId;
    private String date;
    private TimeAvailability timeAvailability;
    private String startTime;
    private String endTime;
    private Status availability;
    private CustomerOrderDto customerOrderDto;

    private String buyerEmail;
    private double price;
    private  long orderId;

}
