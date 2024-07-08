package com.stackroute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomEmail {
    private String subject;
    private String receiver;
    private String userName;
}
