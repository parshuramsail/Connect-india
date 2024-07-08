package com.stackroute.productservice.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.stackroute.productservice.enums.Installation;
import lombok.*;
import org.springframework.data.annotation.Id;
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

    @Id
    private long pid;

    @NotBlank(message = "Plan name is mandatory")
    private String planName;

    @NotBlank(message = "Network Speed  is mandatory")
    private String networkSpeed;

    @NotBlank(message = "Data  is mandatory")
    private String data;

    @NotBlank(message = "Price  is mandatory")
    private  double price;

    private String duration;

    private String offers;

    private Installation installation;

    private  String buyerEmail;


}
