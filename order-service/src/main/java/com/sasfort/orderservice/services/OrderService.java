package com.sasfort.orderservice.services;

import com.sasfort.orderservice.dto.OrderRequest;
import com.sasfort.orderservice.dto.OrderResponse;
import com.sasfort.orderservice.models.Order;
import com.sasfort.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final WebClient webClient;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .bookId(orderRequest.getBookId())
                .quantity(orderRequest.getQuantity())
                .build();

        Boolean bookExist = webClient.get()
                .uri("http://localhost:8000/api/books/" + orderRequest.getBookId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (bookExist != null && bookExist) {
            orderRepository.save(order);
            log.info("Order {} is saved.", order.getId());

            return mapToOrderResponse(order);
        } else {
            throw new IllegalArgumentException("Book does not exist");
        }
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .bookId(order.getBookId())
                .quantity(order.getQuantity())
                .build();
    }
}
