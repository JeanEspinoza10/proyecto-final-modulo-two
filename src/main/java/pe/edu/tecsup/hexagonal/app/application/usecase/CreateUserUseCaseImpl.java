package pe.edu.tecsup.hexagonal.app.application.usecase;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.hexagonal.app.application.port.input.CreateUserUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.output.UserRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidUserDataException;
import pe.edu.tecsup.hexagonal.app.domain.exception.UserNotFoundException;
import pe.edu.tecsup.hexagonal.app.domain.model.User;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(User newUser) {

        if (newUser == null) {
            throw new InvalidUserDataException("User cannot be null");
        }

        // Domain validation
        newUser.validateUserInput();

        // Business rule: Check if email already exists
        if (userRepositoryPort.existsByEmail(newUser.getEmail())) {
            throw new InvalidUserDataException("Email already exists: " + newUser.getEmail());
        }

        return userRepositoryPort.save(newUser);
    }


/*
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return userRepositoryPort.existsByEmail(email);
    }
*/

}
