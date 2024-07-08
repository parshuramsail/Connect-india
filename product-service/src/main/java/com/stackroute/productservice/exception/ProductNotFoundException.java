package com.stackroute.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Product not found exception
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundException extends  RuntimeException{
    private String message;
}
