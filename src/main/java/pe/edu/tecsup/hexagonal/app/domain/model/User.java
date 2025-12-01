package pe.edu.tecsup.hexagonal.app.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidUserDataException;

import java.time.LocalDateTime;

@Getter
//@Setter
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    // Nombres
    private String name;
    private String lastname;

    // Control
    private String status;  // ACTIVE, INACTIVE
    private String role;    // USER, ADMIN

    // Auditoría
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void validateUserInput() {

        if (!this.hasValidName()) {
            throw new InvalidUserDataException("Name must be at least 2 characters long");
        }

        if (!this.hasValidEmail()) {
            throw new InvalidUserDataException("Invalid email format");
        }
    }


    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }

    public boolean hasValidName() {
        return name != null &&
                !name.trim().isEmpty() &&
                name.length() >= 2;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', email='%s'}", id, name, email);
    }

    public void updateDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

}