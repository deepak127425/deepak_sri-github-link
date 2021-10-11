package com.gateway.Paymentservice.Repository;

import com.gateway.Paymentservice.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PaymentRepository extends MongoRepository<Payment, Integer> {

   List<Payment> findByOrderId(int orderId);
}
