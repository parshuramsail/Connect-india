package com.stackroute.orderservice.service;
import java.util.List;
import com.stackroute.orderservice.dto.CustomerOrderDto;
import com.stackroute.orderservice.dto.PlanDto;
public interface CustomerOrderService {
	
	List<CustomerOrderDto> fetchAllOrders();
	
	CustomerOrderDto addOrder(PlanDto planDto);
	
	CustomerOrderDto fetchOrderById(Long id);

	long deleteOrderById(Long id);





}
