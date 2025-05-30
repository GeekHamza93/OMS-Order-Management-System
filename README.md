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

# code-with-quarkus-oms

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-oms-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.



### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### Messaging codestart

Use Quarkus Messaging

[Related Apache Kafka guide section...](https://quarkus.io/guides/kafka-reactive-getting-started)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
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
