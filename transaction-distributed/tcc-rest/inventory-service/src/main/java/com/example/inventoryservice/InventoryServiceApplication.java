package com.example.inventoryservice;

import com.example.inventoryservice.dao.InventoryDao;
import com.example.inventoryservice.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.inventoryservice.dao")
@EnableTransactionManagement
@EnableJpaAuditing
public class InventoryServiceApplication implements CommandLineRunner {
    @Autowired
    InventoryDao inventoryDao;

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Inventory inventory = Inventory.builder()
            .leftNum(100)
            .productCode("spring-cloud-in-action")
            .build();
        inventoryDao.save(inventory);
    }
}
