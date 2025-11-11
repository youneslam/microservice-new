package com.dev.inventory_service.controller;

import com.dev.inventory_service.dto.InventoryResponse;
import com.dev.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        System.out.println("==> skuCode reçu dans InventoryController: " + skuCode);
        return inventoryService.isInStock(skuCode);
    }


}
