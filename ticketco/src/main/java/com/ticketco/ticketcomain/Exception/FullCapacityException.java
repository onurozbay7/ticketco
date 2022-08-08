package com.ticketco.ticketcomain.Exception;

public class FullCapacityException extends Throwable {
    private static final String FULL_CAPACITY = "Maalesef yeterli bilet bulunmamaktadır.";

    public FullCapacityException(){
        super(FULL_CAPACITY);
    }
}
