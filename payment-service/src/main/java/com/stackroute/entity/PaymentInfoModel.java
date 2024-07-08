package com.stackroute.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PaymentInfoModel {

	private String orderId;
	private BigInteger amount;

	@Id
	private Integer bookingId;
	
	private String userEmail;

}
