package com.serverless.awsspringperson.conf;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.serverless.awsspringperson.person")
public class DynamoDBConfig {

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
				.withRegion(Regions.US_EAST_1)
				.build();

		return dynamoDBClient;
	}
}
