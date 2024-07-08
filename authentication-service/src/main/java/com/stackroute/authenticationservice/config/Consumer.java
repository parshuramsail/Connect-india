package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.dto.UserDto;
import com.stackroute.authenticationservice.model.JwtUser;
import com.stackroute.authenticationservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    UserService userService;

    @RabbitListener(queues = "user_queue")
    public void getUserFromRabbitMq(UserDto userDto) {
        JwtUser user = new JwtUser();
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getUserRole());
        userService.addUser(user);
    }
}
