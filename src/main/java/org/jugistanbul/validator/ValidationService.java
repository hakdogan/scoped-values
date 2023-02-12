package org.jugistanbul.validator;

import org.jugistanbul.PaymentGateway;
import org.jugistanbul.dto.PaymentRequest;

public class ValidationService
{
    public static boolean checkValidity(){
        PaymentRequest paymentRequest = PaymentGateway.PAYMENT_REQUEST.get();
        return checkNumber(paymentRequest.cardNumber());
    }

    private static boolean checkNumber(final String cardNumber){
        if(cardNumber.isEmpty() || cardNumber.isBlank()){
            throw new RuntimeException("Invalid Credit Card Number!");
        }
        return true;
    }
}
