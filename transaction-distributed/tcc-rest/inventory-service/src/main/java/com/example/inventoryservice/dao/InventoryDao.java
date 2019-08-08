package com.example.inventoryservice.dao;

import com.example.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDao extends JpaRepository<Inventory, Integer> {
    Inventory findByProductCode(String productCode);
}
