package com.example.atomikosjta;

import com.example.atomikosjta.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtomikosJtaApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        boolean order = orderService.createOrder1();
    }

}
