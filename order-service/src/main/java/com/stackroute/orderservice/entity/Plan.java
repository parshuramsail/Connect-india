package com.stackroute.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stackroute.orderservice.enums.Installation;
import lombok.*;
import org.springframework.data.annotation.Transient;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Plan {

    @Transient
    public static final String SEQUENCE_NAME = "plan_sequence";
    private long pid;
    private String planName;
    @NotBlank(message = "Price  is mandatory")
    private  double price;
    private String duration;
    private String offers;
    private Installation installation;

    private String buyerEmail;


}