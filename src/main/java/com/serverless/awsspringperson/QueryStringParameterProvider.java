package com.serverless.awsspringperson;

import java.util.Map;

public interface QueryStringParameterProvider {

    Map<String, String> getQueryStringParameters();

    boolean hasQueryStringParameter(String name);

    String getQueryStringParameter(String name);

}
