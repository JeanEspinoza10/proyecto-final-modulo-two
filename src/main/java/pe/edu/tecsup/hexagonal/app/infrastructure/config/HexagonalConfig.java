package pe.edu.tecsup.hexagonal.app.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.edu.tecsup.hexagonal.app.application.port.input.*;
import pe.edu.tecsup.hexagonal.app.application.port.output.*;
import pe.edu.tecsup.hexagonal.app.application.usecase.*;
import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;

@Configuration
public class HexagonalConfig {

    @Bean
    public CustomerUseCase customerUseCase(CustomerRepositoryPort customerRepositoryPort){
        return new CustomerUseCaseImpl(customerRepositoryPort);
    }

    @Bean
    public BankAccountUseCase bankAccountUseCase(BankAccountRepositoryPort bankAccountRepositoryPort, CustomerRepositoryPort customerRepositoryPort){
        return new BankAccountUseCaseImpl(bankAccountRepositoryPort, customerRepositoryPort);
    }

    @Bean
    public TransactionUseCase transactionUseCase(TransactionRepositoryPort transactionRepositoryPort, BankAccountRepositoryPort bankAccountRepositoryPort, CommissionCalculatorPort commissionCalculatorPort, NotificationPort notificationPort){
        return new TransactionUseCaseImpl(transactionRepositoryPort,bankAccountRepositoryPort,commissionCalculatorPort,notificationPort );
    }

}
