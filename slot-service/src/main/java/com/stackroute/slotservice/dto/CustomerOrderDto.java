
package com.stackroute.slotservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderDto {
	
	private Long orderId;
	private double price;
	private Date orderDate = Calendar.getInstance().getTime();

	private String buyerEmail;

}
