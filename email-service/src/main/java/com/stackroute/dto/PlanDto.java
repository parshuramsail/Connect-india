package com.stackroute.dto;
import lombok.*;


//DTO class for plan
@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {


    private long pid;

    private String planName;

    private String networkSpeed;

    private String data;

    private  double price;

    private String duration;

    private String offers;




}
