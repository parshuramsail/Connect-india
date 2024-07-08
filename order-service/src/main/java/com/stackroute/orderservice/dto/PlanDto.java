package com.stackroute.orderservice.dto;


import com.stackroute.orderservice.enums.Installation;
import lombok.*;

//DTO class for plan

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanDto {



    private long pid;


    private String planName;

    private  double price;

    private String duration;

    private String offers;

    private Installation installation;

    private String buyerEmail;




}
