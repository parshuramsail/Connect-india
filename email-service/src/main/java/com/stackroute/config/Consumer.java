package com.stackroute.config;

import com.stackroute.dto.ProductDto;
import com.stackroute.dto.UserDto;
import com.stackroute.entity.CustomEmail;
import com.stackroute.entity.PurchaseDetails;
import com.stackroute.service.EmailServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    Log log = LogFactory.getLog(Consumer.class);

    @Autowired
    EmailServiceImpl emailService;

    @RabbitListener(queues = "email_queue")
    public void getRegisteredUser(UserDto userDto) {

        log.info("Data is consumed from Rabbit mq");
        log.info(userDto.toString());
        CustomEmail email = new CustomEmail();
        String userName = userDto.getUserName();
        email.setReceiver(userDto.getEmailId());
        email.setSubject("Registration successful");
        email.setUserName(userName);
        emailService.registrationCompleteEmail(email);
    }

    @RabbitListener(queues = "product_email_queue")
    public void getNewProduct(ProductDto productDto) {

        log.info(productDto.toString());
        PurchaseDetails purchaseDetails = new PurchaseDetails();
        purchaseDetails.setProduct(productDto.getBrand());
        purchaseDetails.setPlans(productDto.getPlanDto());
        emailService.addedNewProductEmail(purchaseDetails);
    }
}
