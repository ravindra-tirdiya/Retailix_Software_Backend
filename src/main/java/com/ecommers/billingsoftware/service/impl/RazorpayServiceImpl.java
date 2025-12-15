package com.ecommers.billingsoftware.service.impl;

import com.ecommers.billingsoftware.io.OrderResponse;
import com.ecommers.billingsoftware.io.RazorpayOrderResponse;
import com.ecommers.billingsoftware.service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RazorpayServiceImpl implements RazorpayService {
    @Value( "${rezorpay.api-key}")
    private String razorpayKey;
    @Value( "${rezorpay.secret-key}")
    private String razorpaySecret;
    @Override
    public RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

        int amountInPaise = (int) Math.round(amount * 100);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());
        orderRequest.put("payment_capture", 1);

        Order order = razorpayClient.orders.create(orderRequest);
        return convertToResponse(order);
    }


    private RazorpayOrderResponse convertToResponse(Order order) {

        Object createdAtObj = order.get("created_at");

        Long createdAt = null;

        if (createdAtObj instanceof Integer i) {
            createdAt = i.longValue();
        } else if (createdAtObj instanceof Long l) {
            createdAt = l;
        } else if (createdAtObj instanceof Date d) {
            createdAt = d.getTime();
        }

        return RazorpayOrderResponse.builder()
                .id((String) order.get("id"))
                .entity((String) order.get("entity"))
                .amount((Integer) order.get("amount"))
                .currency((String) order.get("currency"))
                .status((String) order.get("status"))
                .receipt((String) order.get("receipt"))
                .created_at(createdAt)
                .build();
    }

}
