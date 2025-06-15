# System-for-Car-Service-Company-using-Spring-Boot
A comprehensive Spring Boot application for managing car service records, featuring role-based access control with JWT security and unit testing with Mockito framework

# Car Service Management System

A comprehensive Spring Boot application for managing car service records, featuring role-based access control with JWT security for RESTful APIs.

## Features

* **CRUD Operations:** Full Create, Read, Update, and Delete functionality for car service details and associated customer addresses.
* **JWT Security:** End-to-end security using JSON Web Tokens for authentication and authorization.
* **Role-Based Access Control (RBAC):** Endpoints for creating, updating, and deleting records are restricted to users with an `ADMIN` role.
* **Robust Validation:** Server-side validation on all incoming data using Jakarta Bean Validation.
* **Global Exception Handling:** Centralized exception handling provides consistent and user-friendly error responses.
* **API Documentation:** Integrated OpenAPI (Swagger) for clear, interactive API documentation.
* **Unit & Integration Testing:** Comprehensive test suite using JUnit 5, Mockito, and Spring Boot Test.

## Tech Stack

* **Backend:** Java 17+, Spring Boot 3+
* **Security:** Spring Security, JSON Web Tokens (JJWT)
* **Database:** Spring Data JPA, Hibernate, H2 Database (for testing/dev), can be configured for any SQL database (e.g., MySQL, PostgreSQL).
* **Testing:** JUnit 5, Mockito, Spring Boot Test, Spring Security Test, MockMvc
* **Build Tool:** Maven

## API Endpoints

All endpoints are prefixed with `/api`.

| HTTP Method | Endpoint                       | Description                                | Access Control |
| :---------- | :----------------------------- | :----------------------------------------- | :------------- |
| `POST`      | `/auth/login`                  | Authenticates a user and returns a JWT.    | Public         |
| `GET`       | `/carservice`                  | Retrieves all car service records.         | Authenticated  |
| `GET`       | `/carservice/{id}`             | Retrieves a car service record by its ID.  | Authenticated  |
| `POST`      | `/carservice/save`             | Creates a new car service record.          | **ADMIN only** |
| `PUT`       | `/carservice/{id}`             | Updates an existing car service record.    | **ADMIN only** |
| `DELETE`    | `/carservice/{id}`             | Deletes a car service record by its ID.    | **ADMIN only** |

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/car-service-management-spring-boot.git](https://github.com/your-username/car-service-management-spring-boot.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd car-service-management-spring-boot
    ```
3.  **Run the application using Maven:**
    ```bash
    mvn spring-boot:run
    ```
4.  The application will start on `http://localhost:8080`.

## Configuration

Key configuration properties can be found in `src/main/resources/application.properties`.

* `app.jwt.secret`: A Base64 encoded secret key for signing JWTs.
* `app.jwt.expiry.millis`: The expiration time for JWTs in milliseconds.
* `spring.datasource.*`: Standard Spring Boot properties for configuring the database connection.
