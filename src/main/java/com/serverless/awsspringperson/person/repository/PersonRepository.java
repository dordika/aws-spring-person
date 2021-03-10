package com.serverless.awsspringperson.person.repository;

import com.serverless.awsspringperson.person.domain.Person;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface PersonRepository extends CrudRepository<Person, String> {

}
