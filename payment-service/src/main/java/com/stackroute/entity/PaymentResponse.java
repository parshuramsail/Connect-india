package com.stackroute.entity;

import lombok.Data;

@Data
public class PaymentResponse {
	String secretKey;
	public String razorpayOrderId;
	String amount;
	String secretId;
	String pageName;
	//public String PaymentId;


}
