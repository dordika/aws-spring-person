# Serverless REST API application based on Spring Boot Cloud Functions using DynamoDB deployed with 'The Serverless Framework' on AWS
 
This simple serverless service was supposed to create 2 lambda functions from the following spring functions:  
- Create Person
- Get person

Once deployed on AWS, I realized that it is not possible to map every function to an independent lambda. 
But it is necessary to deploy a jar for each function and configure on AWS which spring function to invoke.

https://cloud.spring.io/spring-cloud-function/multi/multi__serverless_platform_adapters.html

This project is part my experiments in serverless architecture.