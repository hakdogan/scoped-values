package org.jugistanbul.service;

import org.jugistanbul.PaymentGateway;
import org.jugistanbul.dto.PaymentRequest;

public class PrintService
{
    public static void printPaymentInfo(){
        PaymentRequest request = PaymentGateway.PAYMENT_REQUEST.get();
        System.out.println(String.format("Dear %s we charged your payment successfully from your card", request.customerName()));
    }
}
