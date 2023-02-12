package org.jugistanbul.service;

import jdk.incubator.concurrent.ScopedValue;
import jdk.incubator.concurrent.StructuredTaskScope;
import org.jugistanbul.PaymentGateway;
import org.jugistanbul.dto.PaymentRequest;
import org.jugistanbul.validator.ValidationService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PaymentService
{

    public static void getPaidByCreditCard(){

        PaymentRequest request = PaymentGateway.PAYMENT_REQUEST.get();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<Boolean> validation  = scope.fork(() -> ValidationService.checkValidity());
            Future<Boolean> persist = scope.fork(() -> PersistenceService.persist());

            try {
                scope.join();
                scope.throwIfFailed();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(validation.resultNow() && persist.resultNow()){
                ScopedValue.where(PaymentGateway.PAYMENT_REQUEST, request.copyOf())
                        .run(() -> getPaid());
            }
        }
    }

    private static void getPaid(){
        PaymentRequest request = PaymentGateway.PAYMENT_REQUEST.get();
        //here handle requests for paid
        System.out.println(String.format("Dear %s we charged your payment successfully from your card", request.customerName()));
    }
}
