package com.gateway.Paymentservice.Controller;

import com.gateway.Paymentservice.Model.Payment;
import com.gateway.Paymentservice.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody  Payment payment){
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public List<Payment> findPaymentHistoryByOrderId(@PathVariable  int orderId){
        return service.findPaymentHistoryByOrderId(orderId);
    }

}
