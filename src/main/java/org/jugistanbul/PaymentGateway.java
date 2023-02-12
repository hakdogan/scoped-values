package org.jugistanbul;

import jdk.incubator.concurrent.ScopedValue;
import org.jugistanbul.dto.PaymentRequest;
import org.jugistanbul.processor.PaymentProcessor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.*;

public class PaymentGateway
{
    public static final ScopedValue<PaymentRequest> PAYMENT_REQUEST = ScopedValue.newInstance();

    public static void main(String[] args) throws InterruptedException {

        try (var executor = newVirtualThreadPerTaskExecutor()) {
            IntStream
                    .range(0, 10)
                    .mapToObj(PaymentGateway::createPaymentRequest)
                    .map(PaymentProcessor::createPaymentTask)
                    .forEach(executor::submit);

            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    private static PaymentRequest createPaymentRequest(final int requestId){

        var customerId = String.valueOf(ThreadLocalRandom.current().nextLong(9999));
        var customerName = generateAlphanumericString(97, 122, 8);

        var cardNumber = String.join(" ",
                generateAlphanumericString(48, 57, 4),
                generateAlphanumericString(48, 57, 4),
                generateAlphanumericString(48, 57, 4),
                generateAlphanumericString(48, 57, 4));

        var cardHolderName = String.join(" ",
                customerName,
                generateAlphanumericString(97, 122, 8));

        var expirationDate = generateExpirationDate();

        return new PaymentRequest(requestId, customerId, customerName, cardNumber,
                cardHolderName, expirationDate, generateAlphanumericString(48, 57, 3));
    }

    private static String generateAlphanumericString(final int leftLimit,
                                                     final int rightLimit,
                                                     final int length){
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String generateExpirationDate(){

        var month = ThreadLocalRandom.current().nextInt(1, 12);
        var year = ThreadLocalRandom.current().nextInt(22, 30);

        var formattedMonth = month < 10
                ? "0" + month
                : String.valueOf(month);

        return String.join("/", formattedMonth, String.valueOf(year));
    }
}