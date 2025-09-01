package net.developer.space.productwarehouseservice.warehouse.application.exceptions;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message){
        super(message);
    }

}
