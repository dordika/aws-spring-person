package com.serverless.awsspringperson.person.service;

import com.serverless.awsspringperson.person.domain.Person;
import com.serverless.awsspringperson.person.dto.PersonPojo;

public interface PersonService {

	Person savePerson(PersonPojo requestData) throws Exception;

}
