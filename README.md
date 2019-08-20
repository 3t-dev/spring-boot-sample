# Order Management Service - A Spring boot application sample

## Introduction

This is a Spring boot application sample that show some technical features:

* How to create a POST api using Spring boot framework.
  * RestAPI POST /orders.
  * JPA for persistent data.
  * Rest exception handle mechanism.
* Application layer follow Clean architecture.
* UT includes logic test and MVC integration test.

## Get started

### Configuration

#### Server port

Change this value in **bootstrap.yml**:
```yaml
server:
  port: 8080
```

#### Data source

By default, this application uses H2 database for easy start. It can be change by re-config **bootstrap.yml** file.

### Run test

```
mvn test
```
Jacoco report is generated in **target/jacoco-ut/index.html**.

### Run application

```
mvn spring-boot:run
```

APIs is available:
* API docs: GET localhost:8080/v2/api-docs
* New order: POST localhost:8080/orders

## Contact
Email to <trinhthethanh25390@gmail.com>
