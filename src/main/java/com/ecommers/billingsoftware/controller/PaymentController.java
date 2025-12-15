package com.ecommers.billingsoftware.controller;

import com.ecommers.billingsoftware.io.OrderResponse;
import com.ecommers.billingsoftware.io.PaymentRequest;
import com.ecommers.billingsoftware.io.PaymentVerificationRequest;
import com.ecommers.billingsoftware.io.RazorpayOrderResponse;
import com.ecommers.billingsoftware.service.OrderService;
import com.ecommers.billingsoftware.service.RazorpayService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final RazorpayService razorpayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    private RazorpayOrderResponse createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return razorpayService.createOrder(request.getAmount(), request.getCurrency());
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request){
        return orderService.verifyPayment(request);
    }
}
