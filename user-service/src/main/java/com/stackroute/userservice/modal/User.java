package com.stackroute.userservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "users")
public class User {

    @MongoId
    @Email
    @NotBlank(message = "EmailId is mandatory")
    @Indexed(unique=true)
    private	String emailId;

    @NotBlank(message = "userName is mandatory")
    @Size(min = 3, max = 20)
    @Indexed(unique=true)
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 15)
    private String password;

    @NotBlank(message = "Contact number is mandatory")
    private String contactNo;
    private String alternateNo;

    @NotBlank(message = "User Role is mandatory")
    private UserRole userRole;

    @NotBlank(message = "Address is mandatory")
    private Address address;
}
