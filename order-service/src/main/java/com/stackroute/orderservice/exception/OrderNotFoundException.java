package com.stackroute.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {
    String msg;

    public OrderNotFoundException(String msg) {
        super();
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

}
