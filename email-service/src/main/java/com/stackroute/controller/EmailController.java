package com.stackroute.controller;

import com.stackroute.entity.CustomEmail;
import com.stackroute.entity.PurchaseDetails;
import com.stackroute.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Value("${successEmail}")
    private String successEmailMessage;

    @Value("${failEmail}")
    private  String failMessage;



//    @GetMapping("/sendOfferEmail")
//    public EmailResponse sendOfferEmail() {
//        return emailService.sendOfferEmail();
//    }

    @PostMapping("/registrationComplete")
    public ResponseEntity<String> registrationComplete(@RequestBody CustomEmail email){
        if (Boolean.TRUE.equals(emailService.registrationCompleteEmail(email)))
        {
            return new ResponseEntity<>(successEmailMessage, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(failMessage, HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @PostMapping("/addedNewProductEmail")
    public ResponseEntity<String> addedNewProductEmail(@RequestBody PurchaseDetails purchase){
        if (Boolean.TRUE.equals(emailService.addedNewProductEmail(purchase)))
        {
            return new ResponseEntity<>(successEmailMessage, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(failMessage, HttpStatus.REQUEST_TIMEOUT);
        }
    }

//    @PostMapping("/purchaseProductEmail")
//    public ResponseEntity<String> purchaseProductEmail(@RequestBody CustomEmail email, PurchaseDetails purchase){
//        if (Boolean.TRUE.equals(emailService.purchaseProductEmail(email,purchase)))
//        {
//            return new ResponseEntity<>(successEmailMessage, HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(failMessage, HttpStatus.REQUEST_TIMEOUT);
//        }
//    }


}
