package com.serverless.awsspringperson.function;

import com.serverless.awsspringperson.AbstractAwsApiGatewayLambdaFunction;
import com.serverless.awsspringperson.BasicResponse;
import com.serverless.awsspringperson.Request;
import com.serverless.awsspringperson.Response;
import com.serverless.awsspringperson.person.domain.Person;
import com.serverless.awsspringperson.person.dto.PersonPojo;
import com.serverless.awsspringperson.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PostPerson extends AbstractAwsApiGatewayLambdaFunction<PersonPojo, PersonPojo> {

    private static Logger logger = LoggerFactory.getLogger(PostPerson.class);

    @Autowired
    PersonService personService;

    public PostPerson() {
        super(PersonPojo.class);
    }

    @Override
    public Response<PersonPojo> apply(Request<PersonPojo> request) {
        logger.info("Execute Lambda createPerson");
        PersonPojo personPojo = request.getBody();
        Person person = personService.savePerson(personPojo);
        return BasicResponse.<PersonPojo>builder()
                .statusCode(201)
                .body(personPojo)
                .build();
    }
}
