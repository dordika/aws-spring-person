package com.serverless.awsspringperson;

public interface Response<I> extends HeaderProvider {

    I getBody();

    int getStatusCode();

}
