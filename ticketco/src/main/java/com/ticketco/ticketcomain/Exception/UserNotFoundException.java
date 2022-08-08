package com.ticketco.ticketcomain.Exception;

public class UserNotFoundException extends Throwable {
    private static final String USER_NOT_FOUND = "Kullanıcı bulunamadı";

    public UserNotFoundException(){
        super(USER_NOT_FOUND);
    }
}
