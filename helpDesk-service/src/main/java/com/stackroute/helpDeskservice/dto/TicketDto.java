package com.stackroute.helpDeskservice.dto;

import com.stackroute.helpDeskservice.enums.Priority;
import com.stackroute.helpDeskservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private long ticketId;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Please provide proper description for better understanding of the issue")
    private String description;
    private Date createdDate;
    private String reporterEmailId;
    private String assigneeEmailId;
    private Priority priority;
    private Status status;
    private String comments;


}
