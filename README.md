# RESTful Web Services

A learning project for building REST APIs with Spring Boot.

## Tech Stack

- Java 26
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Validation
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI / Swagger UI
- Maven

## Features

- Hello World REST endpoints
- User REST API
- Request validation
- Custom error response handling
- Internationalized message example
- Swagger/OpenAPI documentation

## API Endpoints

### Hello World

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/hello-world` | Returns a simple text response |
| GET | `/hello-world-bean` | Returns a JSON response |
| GET | `/hello-world/PathVariable/{name}` | Returns a message with path variable |
| GET | `/hello-world-Internationalized` | Returns an internationalized message |

### Users

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get one user by ID |
| POST | `/users` | Create a new user |
| DELETE | `/users/{id}` | Delete a user by ID |

## Run Locally

Clone the repository:

```bash
git clone https://github.com/amsingh0703/-restful-web-services.git
cd -restful-web-services
```

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## Swagger / OpenAPI

After starting the application, open Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON is available at:

```text
http://localhost:8080/v3/api-docs
```

Swagger helps you view and test all REST endpoints from the browser.

## Example JSON For Creating A User

```json
{
  "name": "Amit",
  "birthDate": "2000-01-01"
}
```

## Validation Rules

- `name` must contain 2 to 20 characters.
- `birthDate` must be today or a past date.

## Notes

This project currently stores users in an in-memory list. The data will reset when the application restarts.
