package com.programming.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programming.orderservice.dto.*;
import com.programming.orderservice.model.Order;
import com.programming.orderservice.model.OrderLineItems;
import com.programming.orderservice.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;

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

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        // call inventory server and place order if product is inStock
        InventoryResponse[] inventoryResponseArray = webClient.get()
        .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
        .retrieve().bodyToMono(InventoryResponse[].class).block();

        // check if each product in the orderLineItems is inStock
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        
        if(allProductsInStock){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Produt is not in stock, please try again later!");
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