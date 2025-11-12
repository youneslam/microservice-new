package com.dev.order_service.service;

import com.dev.order_service.dto.InventoryResponse;
import com.dev.order_service.dto.OrderLineItemsDto;
import com.dev.order_service.dto.OrderRequest;
import com.dev.order_service.model.Order;
import com.dev.order_service.model.OrderLineItems;
import com.dev.order_service.repository.OrderRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author DELL
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final WebClient.Builder webClient;
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItemsList);
        List<String> skuCodes=order.getOrderLineItems().stream().map(
                OrderLineItems::getSkuCode
        ).toList();
        //call inventoryService to know if is the order is in stock
        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> {
                    skuCodes.forEach(code -> uriBuilder.queryParam("skuCode", code));
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        assert inventoryResponses != null;
        boolean allProductsInStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);
        if (inventoryResponses.length == 0) {
            throw new IllegalArgumentException("Product not in stock");
        }
        System.out.println("==== INVENTORY RESPONSE DEBUG ====");
        System.out.println(Arrays.toString(inventoryResponses));
        for (InventoryResponse resp : inventoryResponses) {
            System.out.println(resp.getSkuCode() + " -> " + resp.getIsInStock());
        }

        if(allProductsInStock){
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product not in stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
