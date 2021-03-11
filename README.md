# Serverless REST API application based on Spring Boot Cloud Functions using DynamoDB deployed with 'The Serverless Framework' on AWS

This simple serverless service creates 2 lambda functions from the following spring functions:
- Create Person
- Get person

The infrastructure for the serverless application is defined with Serverless Framework.
The data is stored in DynamoDB.

In this branch the functions are declared as Bean Spring objects. 
In the other branch 'function-as-class' of the same repository, functions are defined as class that implements java.util.function.Function 

```
endpoints:
   GET - https://{your_api_gateway_endpoint}/persons/{id}
   POST - https://{your_api_gateway_endpoint}/person
```

This project is part my experiments in serverless architecture.

## Environment Pre-requisites
1) The serverless framework: https://www.serverless.com/framework/docs/providers/aws/guide/installation/
2) Java/Maven

## Build the Java project
From the project folder execute:
```
$ mvn clean install
```

## Deploy the serverless app
```
$ sls deploy
```

## Test the API
In order to test the functions either user Postman locally and invoke accordingly the APIs or go in AWS console and test
directly from the console.