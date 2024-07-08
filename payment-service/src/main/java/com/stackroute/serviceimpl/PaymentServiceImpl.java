package com.stackroute.serviceimpl;

import java.math.BigInteger;
import com.stackroute.entity.PaymentInfoModel;
import com.stackroute.entity.PaymentRequest;
import com.stackroute.entity.PaymentResponse;
import com.stackroute.repository.PaymentRepository;
import com.stackroute.service.PaymentSrevice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


import lombok.Data;
@Service
@Data
public class PaymentServiceImpl implements PaymentSrevice {

	private RazorpayClient client;

	private static final String SECRET_ID1 = "rzp_test_W4YRGR2MY42TcG";
	private static final String SECRET_KEY1 = "EmW9xWISrbEVoTtllJztMRJc";

	@Autowired
	public PaymentRepository repository;

	/**
	 * @description : save the payment Request
	 * @return : Returns payment response
	 */

	@Override
	public PaymentResponse savePaymentRequest(PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse = new PaymentResponse();
		try {
			client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
			Order createRazorPayOrder = createRazorPayOrder(paymentRequest.getAmount());

			String object = (String) createRazorPayOrder.get("id");


			paymentResponse.setSecretId(SECRET_ID1);
			paymentResponse.setSecretKey(SECRET_KEY1);
			paymentResponse.setRazorpayOrderId(object);
			paymentResponse.setAmount("" + paymentRequest.getAmount());
			paymentResponse.setPageName("RazorPay Payment Gateway");
			System.out.println("----orderid:" + object);


			PaymentInfoModel details = new PaymentInfoModel();
			details.setAmount(paymentRequest.getAmount());
			details.setUserEmail(paymentRequest.getEmail());
			

			details.setBookingId(0);
			details.setOrderId(paymentResponse.razorpayOrderId);
			details.setBookingId(setID());
			repository.save(details);


			//Random random = new Random();
			//int x = random.nextInt(100);
			//paymentResponse.setTransactionId(String.valueOf(x));

			System.out.println("Payment details saved with id " + details.getBookingId());

		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return paymentResponse;
	}

	/**
	 * @description : create order using Razorpay Api
	 * @return : Returns order details
	 */

	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1);
		return client.orders.create(options);
	}

	/*@Override
	public Optional<PaymentInfoModel> getPayment(int id) {
		System.out.println("Searching payment with id : " + id);
		System.out.println("DataBase: " + repository.findAll());
		return repository.findById(id);
	}

	@Override
	public Optional<PaymentInfoModel> updatePayment(int id, String status) {
		PaymentInfoModel updateInfo = repository.findById(id).orElseThrow();
		repository.save(updateInfo);
		return Optional.ofNullable(updateInfo);
	}*/

	public int setID() {
		int id = (int) repository.count() + 1;
		return id;
	}

}
