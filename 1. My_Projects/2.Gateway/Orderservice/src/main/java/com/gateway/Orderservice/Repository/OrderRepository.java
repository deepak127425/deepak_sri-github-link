package com.gateway.Orderservice.Repository;

import com.gateway.Orderservice.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, Integer> {
}
