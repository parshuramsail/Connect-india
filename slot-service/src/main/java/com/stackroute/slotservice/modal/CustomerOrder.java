
package com.stackroute.slotservice.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Calendar;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "order")

public class CustomerOrder {

	@MongoId
	private Long orderId;
	private double price;
	private Date orderDate = Calendar.getInstance().getTime();

	private String buyerEmail;


}
