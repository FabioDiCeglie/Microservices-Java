package com.programming.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.programming.orderservice.dto.OrderLineItemsDto;
import com.programming.orderservice.dto.OrderRequest;
import com.programming.orderservice.model.Order;
import com.programming.orderservice.model.OrderLineItems;
import com.programming.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
        .stream()
        .map(this::mapToDto)
        .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItems){
        OrderLineItems orderLineItemsDto = new OrderLineItems();
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        orderLineItemsDto.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItemsDto.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItemsDto;
        }

}

