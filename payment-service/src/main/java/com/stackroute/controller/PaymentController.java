package com.stackroute.controller;

import com.stackroute.entity.PaymentRequest;
import com.stackroute.entity.PaymentResponse;
import com.stackroute.service.PaymentSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	public PaymentSrevice service;

	
	@PostMapping(value="/api/v/payment",consumes = {"application/json"})
	@ResponseBody
	public PaymentResponse payAmount(@RequestBody PaymentRequest paymentRequest) {
		return service.savePaymentRequest(paymentRequest);
	}

	/*@GetMapping("/api/v/payment/{id}")
	@ResponseBody
	public Optional<PaymentInfoModel> getPayments(@PathVariable int id) {
		return service.getPayment(id);
	}

	@PutMapping("api/v/payment/{id}")
	public Optional<PaymentInfoModel> updateOrder(@RequestBody String status, @PathVariable("id") int id){
		return service.updatePayment(id, status);
	}*/

}
