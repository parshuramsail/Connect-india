package com.stackroute.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateRequest {

    @NotBlank(message = "userName is mandatory")
    @Size(min = 3, max = 20)
    @Indexed(unique=true)
    private String userName;

    @NotBlank(message = "contact number is mandatory")
    private String contactNo;
    private String alternateNo;

    @NotBlank(message = "address is mandatory")
    private AddressDto addressDto;
}


