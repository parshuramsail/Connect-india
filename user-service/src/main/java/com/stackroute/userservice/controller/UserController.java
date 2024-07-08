package com.stackroute.userservice.controller;

import com.stackroute.userservice.dto.UserDto;
import com.stackroute.userservice.dto.UserUpdateRequest;
import com.stackroute.userservice.service.UserService;
import com.stackroute.userservice.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * To Get All Users from database
     */
    @GetMapping(value="/fetchAllUsers")
    public ResponseEntity<List<UserDto>> fetchAllPersons() {

        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    /**
     * To Save the user to Database
     */
    @PostMapping(value="/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    /**
     * To Get the User from database
     */
    @GetMapping("/fetch")
    public ResponseEntity<UserDto> fetchUserByEmailId(@RequestParam String  emailId) {
        return new ResponseEntity<>(userService.fetchUserByEmailId(emailId), HttpStatus.OK);
    }

    /**
     * To Update the User
     */
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUserByEmailId(@RequestParam String emailId, @RequestBody UserUpdateRequest userUpdateRequest) {

        return new ResponseEntity<>(userService.updateUserByEmailId(emailId, userUpdateRequest), HttpStatus.OK);
    }

    /**
     * To Delete the User from database
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam String  emailId) {

        userService.deleteUserByEmailId(emailId);
        return new ResponseEntity<>(AppConstant.DELETE_MESSAGE, HttpStatus.OK);
    }
}

