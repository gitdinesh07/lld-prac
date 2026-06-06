package DesignQues.bookmyshow.interfaces;

import DesignQues.bookmyshow.models.*;

public interface  PaymentStrategy {

    boolean Pay(Booking bookingObj);
}
