package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.interfaces.PaymentStrategy;
import DesignQues.bookmyshow.models.Booking;

public class CardPayment implements PaymentStrategy{

    @Override
    public boolean Pay(Booking bookingObj) {
        return true;
    }
}
