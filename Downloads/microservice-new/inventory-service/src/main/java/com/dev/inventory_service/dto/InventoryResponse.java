package com.dev.inventory_service.dto;

import lombok.*;

/**
 * @author DELL
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResponse {
    private String skuCode;
    private Integer quantity;
}
