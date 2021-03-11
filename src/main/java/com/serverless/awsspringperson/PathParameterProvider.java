package com.serverless.awsspringperson;

import java.util.Map;

public interface PathParameterProvider {

    Map<String, String> getPathParameters();

    boolean hasPathParameter(String name);

    String getPathParameter(String name);

}
