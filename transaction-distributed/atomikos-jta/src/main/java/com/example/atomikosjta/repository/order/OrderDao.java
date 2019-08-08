package com.example.atomikosjta.repository.order;

import com.example.atomikosjta.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
