# Order Management System

## Description

This project is an Order Management System (OMS) developed using Java, Quarkus, and Kafka. It leverages MongoDB for data storage and is designed to manage orders, handle events, and integrate with other services using modern cloud-native technologies.

## Technologies Used

- **Java**
- **Quarkus**
- **Apache Kafka**
- **MongoDB**
- **Kubernetes / OpenShift (optional)**
- **REST API with JSON over HTTPS**

## Prerequisites

- Java 17 or higher
- Apache Kafka
- MongoDB
- Maven
- IntelliJ IDEA

## Project Structure

```plaintext
src
├── main
│   ├── java
│   │   ├── com.example.entity
│   │   │   └── Order.java
│   │   ├── com.example.repository
│   │   │   └── OrderRepository.java
│   │   ├── com.example.service
│   │   │   └── OrderService.java
│   │   ├── com.example.resource
│   │   │   └── OrderResource.java
│   │   ├── com.example.kafka
│   │   │   ├── OrderProducer.java
│   │   │   └── OrderConsumer.java
│   ├── resources
│   │   └── application.properties
└── test
    ├── java
    │   └── com.example
    │       └── OrderResourceTest.java
