package com.stackroute.helpDeskservice.modal;


import com.stackroute.helpDeskservice.enums.Priority;
import com.stackroute.helpDeskservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Transient
    public static final String SEQUENCE_NAME = "ticket_sequence";

    @Id
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
