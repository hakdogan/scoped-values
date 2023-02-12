package org.jugistanbul.processor;

import jdk.incubator.concurrent.ScopedValue;
import org.jugistanbul.dto.PaymentRequest;
import org.jugistanbul.service.PaymentService;

import static org.jugistanbul.PaymentGateway.PAYMENT_REQUEST;

public class PaymentProcessor
{
    public static Thread createPaymentTask(final PaymentRequest request){
        return Thread.startVirtualThread(() ->
                ScopedValue.where(PAYMENT_REQUEST, request)
                    .run(() -> PaymentService.getPaidByCreditCard()));
    }
}
