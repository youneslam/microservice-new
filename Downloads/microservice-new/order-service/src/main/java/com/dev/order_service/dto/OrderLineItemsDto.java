package com.dev.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author DELL
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
