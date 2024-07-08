package com.stackroute.service;

import com.stackroute.entity.CustomEmail;
import com.stackroute.entity.EmailResponse;
import com.stackroute.entity.PurchaseDetails;


public interface EmailService {
     EmailResponse sendOfferEmail();

     Boolean registrationCompleteEmail(CustomEmail email);

     Boolean addedNewProductEmail(PurchaseDetails purchase);

     Boolean purchaseProductEmail(CustomEmail email, PurchaseDetails purchase);

}
