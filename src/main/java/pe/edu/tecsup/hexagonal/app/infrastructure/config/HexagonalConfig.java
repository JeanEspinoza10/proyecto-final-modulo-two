package pe.edu.tecsup.hexagonal.app.infrastructure.config;


import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.edu.tecsup.hexagonal.app.application.port.input.CreateUserUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.input.DeleteUserUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.input.FindUserUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.input.UpdateUserUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.output.UserRepositoryPort;
import pe.edu.tecsup.hexagonal.app.application.usecase.CreateUserUseCaseImpl;
import pe.edu.tecsup.hexagonal.app.application.usecase.DeleteUserUseCaseImpl;
import pe.edu.tecsup.hexagonal.app.application.usecase.FindUserUseCaseImpl;
import pe.edu.tecsup.hexagonal.app.application.usecase.UpdateUserUseCaseImpl;

@Configuration
public class HexagonalConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepository) {
        return new CreateUserUseCaseImpl(userRepository);
    }

    @Bean
    public FindUserUseCase findUserUseCase(UserRepositoryPort userRepository) {
        return new FindUserUseCaseImpl(userRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepositoryPort userRepository) {
        return new UpdateUserUseCaseImpl(userRepository);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryPort userRepository) {
        return new DeleteUserUseCaseImpl(userRepository);
    }

}
