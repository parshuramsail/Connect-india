package com.stackroute.orderservice.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.orderservice.dto.CustomerOrderDto;
import com.stackroute.orderservice.service.CustomerOrderService;


@RestController
@RequestMapping("/api/v1")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;


    @Value("${delete.message}")
	private String deleteMessage;


	/**
	 * To Get All CustomerOrder from database
	 */
	@GetMapping("/order/getAll")
	public ResponseEntity<List<CustomerOrderDto>> fetchAllOrders() {

		return new ResponseEntity<>(customerOrderService.fetchAllOrders(), HttpStatus.OK);
	}

	/**
	 * To Save the CustomerOrder to Database
	 */
	@PostMapping("/order")
	public ResponseEntity<CustomerOrderDto> addOrder(@RequestBody CustomerOrderDto customerOrderDto) {

		//return new ResponseEntity<>(customerOrderService.addOrder(customerOrderDto), HttpStatus.CREATED);
		return null;
	}

	/**
	 * To Get the CustomerOrder from database
	 */
	@GetMapping("/order/{id}")
	public ResponseEntity<CustomerOrderDto> fetchOrderById(@PathVariable Long id) {

		return new ResponseEntity<>(customerOrderService.fetchOrderById(id), HttpStatus.OK);
	}


	/**
	 * To Delete the CustomerOrder from database
	 */
	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable Long id) {

		customerOrderService.deleteOrderById(id);

		return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
	}

}
