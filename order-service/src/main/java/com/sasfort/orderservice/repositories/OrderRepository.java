package com.sasfort.orderservice.repositories;

import com.sasfort.orderservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findById(String id);
}
