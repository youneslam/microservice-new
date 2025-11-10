package com.dev.inventory_service;

import com.dev.inventory_service.model.Inventory;
import com.dev.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inv1 = new Inventory();
            inv1.setSkuCode("iphone_13");
            inv1.setQuantity(50);

            Inventory inv2 = new Inventory();
            inv2.setSkuCode("samsung_galaxy_s23");
            inv2.setQuantity(30);

            Inventory inv3 = new Inventory();
            inv3.setSkuCode("macbook_pro");
            inv3.setQuantity(20);

            inventoryRepository.save(inv1);
            inventoryRepository.save(inv2);
            inventoryRepository.save(inv3);

        };
    }
}
