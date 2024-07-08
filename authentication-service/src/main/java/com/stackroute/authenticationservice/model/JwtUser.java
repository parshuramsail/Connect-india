package com.stackroute.authenticationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JwtUser {

    @Id
    //  @GeneratedValue
    private String id = UUID.randomUUID().toString();
    //@Email(message = "Enter a valid email only")
    private String emailId;
    private String password;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserRole roles;
}

