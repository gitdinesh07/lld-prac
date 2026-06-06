package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.interfaces.PaymentStrategy;
import DesignQues.bookmyshow.models.PaymentType;

public class PaymentStrategyFactory {
    
    public PaymentStrategy GetStrategy(PaymentType paymentType){
        switch (paymentType) {
            case PaymentType.CARD:
                return new CardPayment();
            case PaymentType.UPI:
                return new UpiPayment();
            default:
                throw new AssertionError();
        }
    }
}
