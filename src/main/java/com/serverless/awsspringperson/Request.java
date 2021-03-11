package com.serverless.awsspringperson;

public interface Request<I> extends HeaderProvider, PathParameterProvider, QueryStringParameterProvider {

    I getBody();

}
