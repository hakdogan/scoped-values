package org.jugistanbul.service;

import org.jugistanbul.PaymentGateway;
import org.jugistanbul.dto.PaymentRequest;

public class UserService
{
    public static boolean accountChecker(){
        PaymentRequest request = PaymentGateway.PAYMENT_REQUEST.get();
        //account checker code
        return true;
    }
}
