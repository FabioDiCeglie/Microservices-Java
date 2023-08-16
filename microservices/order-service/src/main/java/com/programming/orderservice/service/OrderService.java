package com.programming.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programming.orderservice.dto.OrderLineItemsDto;
import com.programming.orderservice.dto.OrderRequest;
import com.programming.orderservice.model.Order;
import com.programming.orderservice.model.OrderLineItems;
import com.programming.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
        .stream()
        .map(this::mapToDto)
        .toList();

        order.setOrderLineItemsList(orderLineItems);

        // call inventory server and place order if product is inStock
        Boolean result = webClient.get().uri('http://localhost:8082/api/inventory/{}')
                            .retrieve().bodyToMono(Boolean.class).block();
        
        if(result){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException('Produt is not in stock, please try again later!')
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItems){
        OrderLineItems orderLineItemsDto = new OrderLineItems();
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        orderLineItemsDto.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItemsDto.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItemsDto;
        }

}

