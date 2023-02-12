package org.jugistanbul.dto;

public record PaymentRequest(int requestId, String customerId, String customerName,
                             String cardNumber, String cardHolderName,
                             String expirationDate, String cvcCode)
{
    public PaymentRequest copyOf(){
        var maskedName = createMaskedName(this.customerName);
        var maskedCardNumber = createMaskedCardNumber(this.cardNumber);
        return new PaymentRequest(this.requestId, maskValue(this.customerId), maskedName, maskedCardNumber,
                maskValue(this.cardHolderName), maskValue(this.expirationDate),
                maskValue(this.cvcCode));
    }

    private String createMaskedName(final String name){
        return name.substring(0, 1).toUpperCase() + "*".repeat(name.length() - 1);
    }

    private String createMaskedCardNumber(final String cardNumber){
        return String.join(" **** **** ",
                cardNumber.substring(0, 4),
                cardNumber.substring(15, 19));
    }

    private String maskValue(final String value){
        return "*".repeat(value.length());
    }
}
