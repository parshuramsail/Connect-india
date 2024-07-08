package com.stackroute.slotservice.modal;

import com.stackroute.slotservice.enums.Status;
import com.stackroute.slotservice.enums.TimeAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "slot")
public class Slot {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @MongoId
    private long slotId;

    private String date;
    private TimeAvailability timeAvailability;
    private String startTime;
    private String endTime;
    private Status availability;
    public CustomerOrder customerOrder;

    private String buyerEmail;

}
