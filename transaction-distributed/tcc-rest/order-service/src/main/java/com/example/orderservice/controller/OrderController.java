package com.example.orderservice.controller;


import com.example.orderservice.dao.OrderDao;
import com.example.orderservice.entity.OrderState;
import com.example.orderservice.entity.UserOrder;
import com.example.participant.controller.TccParticipantController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by caibosi on 2018-07-27.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends TccParticipantController<UserOrder> {

    @Autowired
    OrderDao orderDao;


    @Override
    public String getParticipantName() {
        return "order-service";
    }

    @Override
    public ResponseEntity executeTry(String txId, UserOrder body) {
        body.setTxId(txId);
        body.setState(OrderState.ORDERED);
        body.setExpireTime(LocalDateTime.now().plusMinutes(30));
        try {
            orderDao.save(body);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @Override
    public ResponseEntity executeCancel(String txId) {
        UserOrder userOrder = orderDao.findByTxId(txId);
        if (userOrder == null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        userOrder.setState(OrderState.CANCELED);
        orderDao.save(userOrder);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity executeConfirm(String txId) {
        UserOrder userOrder = orderDao.findByTxId(txId);
        if (userOrder == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        userOrder.setState(OrderState.CONFIRMED);
        orderDao.save(userOrder);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}