package com.example.inventoryservice.service;

import com.example.inventoryservice.dao.FrozeRequestDao;
import com.example.inventoryservice.dao.InventoryDao;
import com.example.inventoryservice.entity.FrozeRequest;
import com.example.inventoryservice.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 18:53
 */
@Service
public class FrozeService {
    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    FrozeRequestDao frozeRequestDao;

    @Transactional
    public void confirm(FrozeRequest request, Inventory inventory) {
        frozeRequestDao.delete(request);
        int left = inventory.getLeftNum() - request.getFrozenNum();
        if (left < 0) {
            throw new IllegalStateException("inventory left < 0");
        }
        inventory.setLeftNum(left);
        inventoryDao.save(inventory);
    }

    @Transactional
    public void cancel(FrozeRequest request) {
        frozeRequestDao.delete(request);
    }
}
