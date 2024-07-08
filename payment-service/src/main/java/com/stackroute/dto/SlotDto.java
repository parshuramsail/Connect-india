package com.stackroute.dto;

import com.stackroute.enums.Status;
import com.stackroute.enums.TimeAvailability;
import lombok.*;


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
    private String buyerEmail;
    private double price;

    private  long orderId;

}
