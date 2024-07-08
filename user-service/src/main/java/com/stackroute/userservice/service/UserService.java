package com.stackroute.userservice.service;

import com.stackroute.userservice.dto.UserDto;
import com.stackroute.userservice.dto.UserUpdateRequest;
import com.stackroute.userservice.modal.User;

import java.util.List;
public interface UserService {
    List<UserDto> fetchAllUsers();
    UserDto addUser(UserDto userDto);
    UserDto fetchUserByEmailId(String emailId);
    UserDto updateUserByEmailId(String emailId, UserUpdateRequest userUpdateRequest);
    void deleteUserByEmailId(String emailId);
    User convertToEntity(UserDto userDto);
    UserDto convertToDto(User user);
}

