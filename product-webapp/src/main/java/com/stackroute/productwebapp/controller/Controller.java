package com.stackroute.productwebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/")
    public String welcome(Principal principal) {
        return "Your login is successful!! Welcome to Connect India";

    }
}
