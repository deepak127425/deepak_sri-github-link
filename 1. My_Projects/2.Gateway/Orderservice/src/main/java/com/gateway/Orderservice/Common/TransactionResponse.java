package com.gateway.Orderservice.Common;

import com.gateway.Orderservice.Model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Order order;
    private Double orderAmount;
    private String transactionId;
    private String message;
}
