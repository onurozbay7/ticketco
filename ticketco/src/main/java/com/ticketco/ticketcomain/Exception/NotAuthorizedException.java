package com.ticketco.ticketcomain.Exception;

public class NotAuthorizedException extends Throwable {
    private static final String NOT_AUTH= "Lütfen önce giriş yapınız.";

    public NotAuthorizedException() {
        super(NOT_AUTH);
    }
}
