
package com.stackroute.orderservice.entity;


import java.util.Calendar;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "order")

public class CustomerOrder {
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@MongoId
	private Long orderId;


	private double planPrice;

	private Date orderDate = Calendar.getInstance().getTime();
	private Plan plan;
	private String buyerEmail;

}
