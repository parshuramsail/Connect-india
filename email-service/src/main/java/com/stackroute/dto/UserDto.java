package com.stackroute.dto;

import com.stackroute.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String emailId;
    private String userName;
    private UserRole userRole;
}
