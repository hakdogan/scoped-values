package org.jugistanbul.service;

import org.jugistanbul.PaymentGateway;
import org.jugistanbul.dto.PaymentRequest;

public class PersistenceService
{
    public static boolean persist(){
        PaymentRequest request = PaymentGateway.PAYMENT_REQUEST.get();
        //here handle requests for persist
        return true;
    }
}
