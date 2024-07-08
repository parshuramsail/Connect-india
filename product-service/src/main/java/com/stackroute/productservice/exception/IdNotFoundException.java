package com.stackroute.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IdNotFoundException extends  RuntimeException{

    //For ID Not Found Exception
    private String message;
}
