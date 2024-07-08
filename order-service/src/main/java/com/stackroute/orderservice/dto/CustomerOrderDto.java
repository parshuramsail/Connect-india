
package com.stackroute.orderservice.dto;


import java.util.Calendar;
import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderDto {
	
	private Long orderId;

	private double price;

	private Date orderDate = Calendar.getInstance().getTime();
	private  PlanDto planDto;

	private String buyerEmail;

}
