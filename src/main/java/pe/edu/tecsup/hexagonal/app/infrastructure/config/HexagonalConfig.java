package pe.edu.tecsup.hexagonal.app.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.edu.tecsup.hexagonal.app.application.port.input.*;
import pe.edu.tecsup.hexagonal.app.application.port.output.BankAccountRepositoryPort;
import pe.edu.tecsup.hexagonal.app.application.port.output.CustomerRepositoryPort;
import pe.edu.tecsup.hexagonal.app.application.usecase.*;

@Configuration
public class HexagonalConfig {

    @Bean
    public CustomerUseCase customerUseCase(CustomerRepositoryPort customerRepositoryPort){
        return new CustomerUseCaseImpl(customerRepositoryPort);
    }

    @Bean
    public BankAccountUseCase bankAccountUseCase(BankAccountRepositoryPort bankAccountRepositoryPort){
        return new BankAccountUseCaseImpl(bankAccountRepositoryPort);
    }

}
