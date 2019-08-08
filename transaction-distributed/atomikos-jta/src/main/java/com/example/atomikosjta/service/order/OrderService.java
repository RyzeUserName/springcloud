package com.example.atomikosjta.service.order;

import java.math.BigDecimal;

import com.example.atomikosjta.entity.log.Log;
import com.example.atomikosjta.entity.order.Order;
import com.example.atomikosjta.repository.log.LogDao;
import com.example.atomikosjta.repository.order.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 16:37
 */
@Service
public class OrderService {

    @Autowired
    private LogDao logDao;
    @Autowired
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder() {
        try {
            Order order = new Order();
            order.setAmount(200d);
            order.setUsername("小明");
            orderDao.save(order);
            Log log = new Log();
            log.setOperation("小明 创建订单");
            log.setOperator("小明");
            logDao.save(log);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder1() {
        try {
            boolean order = createOrder();
            int i = 1 / 0;
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder2() {
        try {
            Order order = new Order();
            order.setAmount(200d);
            order.setUsername("小明");
            orderDao.save(order);
            int i = 1 / 0;
            Log log = new Log();
            log.setOperation("小明 创建订单");
            log.setOperator("小明");
            logDao.save(log);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

}
