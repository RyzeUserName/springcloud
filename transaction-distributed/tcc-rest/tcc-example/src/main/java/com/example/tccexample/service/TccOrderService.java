package com.example.tccexample.service;

import com.atomikos.tcc.rest.ParticipantLink;
import com.atomikos.tcc.rest.Transaction;
import com.example.tccexample.dto.FrozeRequest;
import com.example.tccexample.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 19:02
 */
@Service
public class TccOrderService {

    @Value("${tcc.participant.orderService}")
    String orderServiceUrlTemplate;
    @Value("${tcc.participant.inventoryService}")
    String inventoryServiceUrlTemplate;
    @Autowired
    TccCoordinatorClient tccCoordinatorClient;
    @Value("${tcc.transaction.timeoutInMs}")
    private long transactionTtimeoutInMs;
    @Autowired
    private RestTemplate restTemplate;

    public void newOrderWithTcc(OrderRequest orderRequest, String txId) {
        long expireTime = System.currentTimeMillis() + transactionTtimeoutInMs;

        List<ParticipantLink> participantLinks = new ArrayList<>(2);
        String orderServiceUrl = String.format(orderServiceUrlTemplate, txId);
        participantLinks.add(new ParticipantLink(orderServiceUrl, expireTime));

        String inventoryServiceUrl = String.format(inventoryServiceUrlTemplate, txId);
        participantLinks.add(new ParticipantLink(inventoryServiceUrl, expireTime));

        Transaction transaction = new Transaction(participantLinks);
        try {

            //1. try participant order-service
            restTemplate.postForEntity(orderServiceUrl, orderRequest, String.class);

            //2. try participant inventory-service
            FrozeRequest frozeRequest = FrozeRequest.builder()
                .productCode(orderRequest.getProductCode())
                .frozenNum(orderRequest.getQuantity())
                .build();
            restTemplate.postForEntity(inventoryServiceUrl, frozeRequest, String.class);

            //3. call coordinator to confirm
            tccCoordinatorClient.confirm(transaction);
        } catch (Exception e) {
            //4. call coordinator to cancel
            tccCoordinatorClient.cancel(transaction);
            String msg = e instanceof HttpStatusCodeException ? ((HttpStatusCodeException) e).getResponseBodyAsString() : e.getMessage();
            throw new RuntimeException(msg, e);
        }
    }
}
