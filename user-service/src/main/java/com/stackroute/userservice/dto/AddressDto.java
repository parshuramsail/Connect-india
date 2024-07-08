package com.stackroute.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {


    private int doorNo;
    private String buildingName;
    private String street;
    @NotBlank(message = "Area is mandatory")
    private String area;
    private String landMark;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "pincode is mandatory")
    private long pinCode;
}

