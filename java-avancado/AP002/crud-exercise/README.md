
# Practical Assessment

Spring Boot application that provides a CRUD API for eCommerce items and includes authentication and password reset functionalities.

### Getting Started

To start the application, run the following command in the root directory of the project:

```
./mvnw spring-boot:run
```

The application will start and listen on port 8080.

### API Documentation

The API documentation is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) after the application is started. It provides detailed information about the available endpoints, their request parameters, and response formats.

### Authentication

Authentication is handled by the `AuthenticationService` class. It uses Spring Security and JWT for authentication. The JWT secret and expiration time can be configured in the `application.properties` file.

### Password Reset

The password reset functionality is provided by the `PasswordResetService` class. It provides two endpoints:

- `/password/request-reset`: This endpoint is used to request a password reset. It accepts a `PasswordResetRequestDTO` object which contains the email of the user who wants to reset their password.
  
- `/password/reset`: This endpoint is used to reset the password.  It receives the user's email, a password reset token, and a `PasswordResetDTO` containing the user's new password.

The password reset process involves generating an OTP, sending it to the user's email, and then validating the OTP when the user attempts to reset their password.

### Email Configuration

The application uses SMTP to send emails. The SMTP server configuration is located in the `application.properties` file. You can configure the SMTP server host, port, username, and password.

### Testing

The application includes unit tests. To run the tests, use the following command:

```
./mvnw test
```

### Building

To build the application, use the following command:

```
./mvnw package
```

This will create a .jar file in the `target` directory.

### Contributing

Contributions are welcome. Please make sure to update tests as appropriate.

### License

MIT
