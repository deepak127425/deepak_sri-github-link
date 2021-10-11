package com.gateway.Orderservice.Service;

import com.gateway.Orderservice.Common.Payment;
import com.gateway.Orderservice.Common.TransactionRequest;
import com.gateway.Orderservice.Common.TransactionResponse;
import com.gateway.Orderservice.Model.Order;
import com.gateway.Orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){

        String response="";

        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setOrderAmount(order.getPrice());

        //rest call
        Payment paymentResponse =   template.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment,Payment.class);

       response =  paymentResponse.getPaymentStatus().equals("success")?"Payment processing successful":"There is an failure in payment api";

        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getOrderAmount(), paymentResponse.getTransactionId(), response);
}

}