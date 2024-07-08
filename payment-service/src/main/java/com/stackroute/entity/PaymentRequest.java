package com.stackroute.entity;

import lombok.Data;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PaymentRequest {
	
	@Id
	String email;
	String phoneNumber;
	BigInteger amount;

	
}
