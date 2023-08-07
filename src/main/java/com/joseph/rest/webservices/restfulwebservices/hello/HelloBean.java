package com.joseph.rest.webservices.restfulwebservices.hello;

public class HelloBean {
    private String message;

    public HelloBean(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
