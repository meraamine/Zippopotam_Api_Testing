Zippopotam API Automation
Overview

This project is a Java-based API automation framework for testing the Zippopotam.us API using Rest Assured and TestNG.

The project focuses on validating valid and invalid postal code requests, response status codes, response structure, and returned data.

The goal is to demonstrate a simple, clean, and maintainable API automation approach suitable for a junior-level automation testing project.

Technologies

Java 21

Rest Assured 5.5.6

TestNG

Maven

IntelliJ IDEA

API Under Test

Base URL:

https://api.zippopotam.us

Endpoint:

GET /{country}/{postal-code}

Example:

GET https://api.zippopotam.us/us/90210

Project Structure

src

 └── test
 
 └── java
 
 ├── base
 
 │   └── BaseTest.java

 
 │
 
 ├── tests
 
 │   ├── ValidZipCodeApiTest.java
 
 │   └── InvalidZipCodeApiTest.java

 │
 
 └── utils

 └── TestData.java

Framework Structure
BaseTest

BaseTest contains the common API configuration used by the test classes.

The base URL is defined once and reused by the tests.

TestData

TestData contains the test data used by the test cases, including valid and invalid country and postal code values.

ValidZipCodeApiTest

Contains positive test scenarios for a valid country and postal code.

The tests verify:

HTTP status code

Response content type

Postal code returned in the response

Country information

Country abbreviation

Presence of the places field

Places list is not empty

Place name exists and is not empty


InvalidZipCodeApiTest

Contains negative test scenarios for invalid and missing input values.

The tests cover:

Invalid country

Invalid postal code

Invalid country and postal code

Missing country

Missing postal code

Test Coverage

The project currently contains 7 test cases.

Valid Scenarios

TC01 - Verify valid postal code response

Send a valid country and postal code.
Verify status code is 200.
Verify the response is JSON.
Verify required response fields and returned postal code.

TC02 - Verify places in response

Verify the places list exists.

Verify the list is not empty.

Verify a place name exists and is not empty.

Invalid Scenarios

TC03 - Verify invalid country

Send an invalid country code with a valid postal code.

Expected status code: 404.

TC04 - Verify invalid postal code

Send a valid country with an invalid postal code.

Expected status code: 404.

TC05 - Verify invalid country and postal code

Send both invalid country and postal code.

Expected status code: 404.

TC06 - Verify missing country

Send a request without the country path parameter.

Verify that the API returns a client error (4xx).

TC07 - Verify missing postal code

Send a request without the postal code path parameter.

Verify that the API returns a client error (4xx).

How to Run

Make sure Java and Maven are installed and configured.

From the project root directory, run:

mvn clean test

Maven will compile the project and execute all TestNG tests.

Design Decisions

The framework was intentionally kept simple and readable.

1. Separate Valid and Invalid Tests

Positive and negative scenarios are separated into different test classes to make the test suite easier to understand and maintain.

2. Reusable Base URL

The API base URL is defined once in BaseTest and reused by the test classes.

3. Centralized Test Data

Test data is stored in TestData instead of being repeated throughout the test methods.

4. Reusable Request Methods

Common API request logic is placed inside helper methods to reduce code duplication while keeping the framework easy to understand.

5. Assertions

TestNG assertions are used to validate both the API response status and important response data.

6. No Postman

The API tests are implemented directly in Java using Rest Assured, as required by the technical task.

Expected Result

When all tests pass successfully:

Tests run: 7

Failures: 0

Errors: 0

The exact Maven/TestNG output may vary depending on the environment.

Conclusion

This project demonstrates a basic API automation framework using Java, Rest Assured, TestNG, and Maven.

The framework focuses on readability, reusable components, clear test scenarios, and meaningful assertions without unnecessary complexity.
