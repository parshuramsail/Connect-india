package com.stackroute.service;




import com.stackroute.entity.PaymentRequest;
import com.stackroute.entity.PaymentResponse;




public interface PaymentSrevice {

	PaymentResponse savePaymentRequest(PaymentRequest paymentRequest);

	/*Optional<PaymentInfoModel> getPayment(int id);

	Optional<PaymentInfoModel> updatePayment(int id, String status);*/
}
