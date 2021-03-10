package com.serverless.awsspringperson.conf;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.awsspringperson.person.domain.Person;
import com.serverless.awsspringperson.person.dto.PersonPojo;
import com.serverless.awsspringperson.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class FunctionConfiguration {
	private static Logger logger = LoggerFactory.getLogger(FunctionConfiguration.class);

	@Autowired
	PersonService personService;
	
	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> createPerson() {
		logger.info("Execute Lambda createPerson");

		return value -> {
			try {
				JsonNode body = new ObjectMapper().readTree(value.getBody());
				PersonPojo personPojo = new PersonPojo(body.get("firstName").asText(), body.get("lastName").asText());
				Person person = personService.savePerson(personPojo);

				return createResponseEvent(person);
			} catch (Exception e) {
				logger.error("Error executing createPerson function", e);
				e.printStackTrace();
				return new APIGatewayProxyResponseEvent().withStatusCode(500);
			}
		};
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getPerson() {
		logger.info("Execute Lambda getPerson");

		return value -> {
			try {
				// get the 'pathParameters' from input
				Map<String,String> pathParameters =  value.getPathParameters();
				String personId = pathParameters.get("id");
				PersonPojo person = personService.get(personId);

				// send the response back
				if (person != null) {
					return createResponseEvent(new ObjectMapper().writeValueAsString(person), 200);
				} else {
					return createResponseEvent("Person with id: '" + personId + "' not found.", 404);
				}
			} catch (Exception e) {
				logger.error("Error in retrieving person", e);
				e.printStackTrace();
				return new APIGatewayProxyResponseEvent().withStatusCode(500);
			}
		};
	}

	private APIGatewayProxyResponseEvent createResponseEvent(Person person) {
		logger.info("Execute createResponseEvent method");
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		ObjectMapper mapper = new ObjectMapper();
		try {
			responseEvent.setStatusCode(201);
			responseEvent.setHeaders(createResultHeader());
			responseEvent.setBody(mapper.writeValueAsString(person));
		} catch (Exception e) {
			logger.error("Error executing createResponseEvent method", e);
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
		return responseEvent;
	}

	private APIGatewayProxyResponseEvent createResponseEvent(String objectBody, int statusCode) {
		logger.info("Execute createResponseEvent method");
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		try {
			responseEvent.setStatusCode(statusCode);
			responseEvent.setHeaders(createResultHeader());
			responseEvent.setBody(objectBody);
		} catch (Exception e) {
			logger.error("Error executing createResponseEvent method", e);
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
		return responseEvent;
	}

	private Map<String, String> createResultHeader() {
		logger.info("Execute createResultHeader method");
		Map<String, String> resultHeader = new HashMap<>();
		resultHeader.put("Content-Type", "application/json");

		return resultHeader;
	}
}