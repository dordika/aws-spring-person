package com.serverless.awsspringperson.person.service.impl;

import com.serverless.awsspringperson.person.domain.Person;
import com.serverless.awsspringperson.person.dto.PersonPojo;
import com.serverless.awsspringperson.person.repository.PersonRepository;
import com.serverless.awsspringperson.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonRepository personRepository;

	@Override
	public Person savePerson(PersonPojo personPojo) throws Exception {
		logger.info("Saving person {} into DB", personPojo);
		Person transientPerson = new Person();
		try {
			// mapping attributes
			transientPerson.setFirstName(personPojo.getFirstName());
			transientPerson.setLastName(personPojo.getLastName());
			// save to DB
			return personRepository.save(transientPerson);
		} catch (Exception e) {
			logger.error("Error saving person ", e);
			throw e;
		}
	}

	@Override
	public PersonPojo get(String id) throws Exception {

		Person person = personRepository.findById(id).orElse(null);
		PersonPojo personPojo = null;

		if (person != null) {
			logger.info("Persons - get(): person - " + person.toString());
			personPojo = new PersonPojo(person.getFirstName(), person.getLastName());
		} else {
			logger.info("Persons - get(): person - Not Found.");
		}

		return personPojo;
	}
}