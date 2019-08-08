package com.example.inventoryservice.dao;

import com.example.inventoryservice.entity.FrozeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 18:53
 */
public interface FrozeRequestDao extends JpaRepository<FrozeRequest, String> {

}
