package pe.edu.tecsup.hexagonal.app.domain.model;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidCustomerException;

import java.time.LocalDateTime;

@Getter
@Builder
public class Customer {

    private final Long id;
    private final String name;
    private final String email;
    private final String document;
    private final LocalDateTime createdAt;

    private Customer(
            Long id,
            String name,
            String email,
            String document,
            LocalDateTime createdAt
    ) {

        validateEmail(email);

        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.createdAt = createdAt;
    }

    public static Customer create(
            Long id,
            String name,
            String email,
            String document
    ) {

        return new Customer(
                id,
                name,
                email,
                document,
                LocalDateTime.now()
        );
    }

    private void validateEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new InvalidCustomerException("Email Cannot be empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidCustomerException("Email invalid Format");
        }
    }

}