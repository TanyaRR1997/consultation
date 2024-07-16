# Getting Started

### Instructions Calling API
Run the Application class in your IDE

Import the postman collection (consultation_postman_collection.json) on your postman and run each with sample request body, etc.
This includes
* Get endpoint to get all consultation questions from in-memory database
* Post endpoint to answer and save the answers to the in-memory database
* Get endpoint to check whether customer is eligible for the prescription from the given answers

The swagger.yaml shows the request and responses of the APIs 

### Some Suggestions for What to Add
* Database table for medicine
* medicineId for Questions, different medicine may require different questions
* Validate required questions are answered
* Database table for customers
* Customer being able to register/login

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.1/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#web)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
