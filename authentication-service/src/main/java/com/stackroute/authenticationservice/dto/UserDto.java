package com.stackroute.authenticationservice.dto;

import com.stackroute.authenticationservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String emailId;
    private String password;
    private UserRole userRole;
}
