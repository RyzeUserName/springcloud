package com.example.orderservice.dao;

import com.example.orderservice.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caibosi on 2018-07-27.
 */
public interface OrderDao extends JpaRepository<UserOrder, Integer> {
    UserOrder findByTxId(String txId);
}