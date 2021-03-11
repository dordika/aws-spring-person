package com.serverless.awsspringperson.function;

import com.serverless.awsspringperson.AbstractAwsApiGatewayLambdaFunction;
import com.serverless.awsspringperson.BasicResponse;
import com.serverless.awsspringperson.Request;
import com.serverless.awsspringperson.Response;
import com.serverless.awsspringperson.person.dto.PersonPojo;
import com.serverless.awsspringperson.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

public class GetPerson extends AbstractAwsApiGatewayLambdaFunction<Object, PersonPojo> {

    private static Logger logger = LoggerFactory.getLogger(GetPerson.class);

    @Autowired
    PersonService personService;

    public GetPerson() {
        super(Object.class);
    }

    @Override
    public Response<PersonPojo> apply(Request<Object> request) {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  request.getPathParameters();
        String personId = pathParameters.get("id");
        logger.info("Retrieving person with ID: " + personId);
        PersonPojo person = personService.get(personId);
        return BasicResponse.<PersonPojo>builder()
                .statusCode(OK.value())
                .body(person)
                .build();
    }
}
