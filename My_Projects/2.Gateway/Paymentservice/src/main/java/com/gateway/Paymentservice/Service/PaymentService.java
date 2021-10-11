package com.gateway.Paymentservice.Service;

import com.gateway.Paymentservice.Model.Payment;
import com.gateway.Paymentservice.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"False";
    }

    public List<Payment> findPaymentHistoryByOrderId(int orderId){
        List<Payment> payments =  repository.findByOrderId(orderId);
        return payments;
    }

}
