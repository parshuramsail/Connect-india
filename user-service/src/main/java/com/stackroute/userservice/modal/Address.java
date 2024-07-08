package com.stackroute.userservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Address")
public class Address {


    private int doorNo;
    private String buildingName;
    private String street;
    @NotBlank(message = "Area is mandatory")
    private String area;
    private String landMark;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "pincode is mandatory")
    private long pinCode;


}
