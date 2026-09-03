# Zippopotam API Automation

A simple API automation framework developed as part of a coding exercise.

The project automates the Zippopotam.us Postal Code API using Java, Rest Assured, TestNG, and Maven.

The main objective is to validate the API behavior for valid and invalid country and postal code inputs.

---

## Technologies

* Java 21
* Rest Assured 5.5.6
* TestNG 7.11.0
* Maven
* IntelliJ IDEA

---

## API Under Test

**Base URL:**

`https://api.zippopotam.us`

**Endpoint:**

`GET /{country}/{postal-code}`

### Example

`GET https://api.zippopotam.us/us/90210`

### Parameters

| Parameter   | Description                        | Example |
| ----------- | ---------------------------------- | ------- |
| Country     | Country code                       | `us`    |
| Postal Code | Postal code for the requested area | `90210` |

---

## Project Structure

```text
Zippopotam_Api_Testing
│
├── pom.xml
├── README.md
│
└── src
    └── test
        └── java
            │
            ├── base
            │   └── BaseTest.java
            │
            ├── tests
            │   ├── ValidZipCodeApiTest.java
            │   └── InvalidZipCodeApiTest.java
            │
            └── utils
                └── TestData.java
```

---

## Framework Structure

### BaseTest

`BaseTest` contains the common API configuration used by the test classes.

The base URL is defined once in the base class and reused by the test classes.

This avoids repeating the API base URL in every test.

### TestData

`TestData` contains the test data used by the automation tests.

The data is encapsulated using private variables and getter methods.

Examples:

* Valid country: `us`
* Valid postal code: `90210`
* Invalid country: `xx`
* Invalid postal code: `00000`

No static test data is used.

### ValidZipCodeApiTest

Contains positive test scenarios using valid country and postal code values.

### InvalidZipCodeApiTest

Contains negative test scenarios using invalid or empty input values.

---

# Test Coverage

The automation suite contains **12 test cases** covering positive and negative scenarios.

## Valid Test Cases

### TC01 - Verify Valid Postal Code Request

**Test:** Send a request using a valid country and valid postal code.

**Expected Result:** HTTP status code should be `200 OK`.

---

### TC02 - Verify Response Country

**Test:** Verify that the response contains the expected country abbreviation.

**Expected Result:** Country abbreviation should be `US`.

---

### TC03 - Verify Response Postal Code

**Test:** Verify that the returned postal code matches the requested postal code.

**Expected Result:** Returned postal code should be `90210`.

---

### TC04 - Verify Places Exist In Response

**Test:** Verify that the `places` field exists in the response.

**Expected Result:** The `places` list should not be null.

---

### TC05 - Verify Places Are Not Empty

**Test:** Verify that the `places` list contains at least one place.

**Expected Result:** The `places` list should not be empty.

---

### TC06 - Verify Place Name Exists

**Test:** Verify that the first returned place contains a place name.

**Expected Result:** The place name should not be null or empty.

---

### TC07 - Verify Response Content Type

**Test:** Verify that the API returns a JSON response.

**Expected Result:** Response content type should contain `application/json`.

---

# Invalid Test Cases

### TC08 - Verify Invalid Country

**Test:** Send a request using an invalid country code.

**Expected Result:** HTTP status code should be `404`.

---

### TC09 - Verify Invalid Postal Code

**Test:** Send a request using a valid country and an invalid postal code.

**Expected Result:** HTTP status code should be `404`.

---

### TC10 - Verify Invalid Country And Postal Code

**Test:** Send a request using both an invalid country and an invalid postal code.

**Expected Result:** HTTP status code should be `404`.

---

### TC11 - Verify Empty Country

**Test:** Send a request without providing a country code.

**Expected Result:** The API should return an HTTP error response.

---

### TC12 - Verify Empty Postal Code

**Test:** Send a request without providing a postal code.

**Expected Result:** The API should return an HTTP error response.

---

# How to Run

## Prerequisites

Make sure the following are installed:

* Java JDK 21
* Maven
* IntelliJ IDEA
* Internet connection

---

## Run Tests Using IntelliJ IDEA

Open the project in IntelliJ IDEA.

The tests can be executed individually:

* `ValidZipCodeApiTest`
* `InvalidZipCodeApiTest`

Or the complete test suite can be executed from the `tests` package.

---

## Run Tests Using Maven

Open a terminal in the project root directory and run:

```bash
mvn clean test
```

The command will clean the previous build, compile the project, and execute the TestNG tests.

---

# Expected Result

All automated tests should pass when the API behaves according to the expected scenarios.

Example:

```text
Total tests run: 12
Passes: 12
Failures: 0
Skips: 0
```

---

# Testing Approach

The tests are divided into two categories:

### Positive Testing

Valid country and postal code values are used to verify that the API returns the expected data and status code.

### Negative Testing

Invalid and empty input values are used to verify that the API handles incorrect requests properly.

The tests also validate important parts of the response, including:

* HTTP status code
* Country abbreviation
* Postal code
* Places list
* Place name
* Response content type

---

# Design Decisions

The framework is intentionally kept simple and easy to maintain because this project is designed for a junior-level coding assessment.

The project uses:

* A base test class for common configuration.
* A separate test data class.
* Separate classes for valid and invalid scenarios.
* Rest Assured for API requests and response validation.
* TestNG for test execution and assertions.
* Maven for dependency management and test execution.

No Postman was used because the exercise specifically requires API automation using code.

---

# Conclusion

This project demonstrates a basic API automation framework using Java, Rest Assured, TestNG, and Maven.

The automation covers both positive and negative scenarios for the Zippopotam.us postal code endpoint while keeping the framework simple, readable, and maintainable.
