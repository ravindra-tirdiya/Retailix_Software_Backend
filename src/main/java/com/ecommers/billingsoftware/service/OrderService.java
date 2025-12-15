package com.ecommers.billingsoftware.service;

import com.ecommers.billingsoftware.io.OrderRequest;
import com.ecommers.billingsoftware.io.OrderResponse;
import com.ecommers.billingsoftware.io.PaymentVerificationRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderResponse verifyPayment(PaymentVerificationRequest request);

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String OrderId);

    List<OrderResponse> readOrders();

    Double sumSalesByDate(LocalDate date);

    Long CountByOrderDate(LocalDate date);

    List<OrderResponse> findRecentOrders();
}
