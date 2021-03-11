package com.serverless.awsspringperson;

import java.util.Map;

public interface HeaderProvider {

    Map<String, String> getHeaders();

    boolean hasHeader(String name);

    String getHeader(String name);

}
