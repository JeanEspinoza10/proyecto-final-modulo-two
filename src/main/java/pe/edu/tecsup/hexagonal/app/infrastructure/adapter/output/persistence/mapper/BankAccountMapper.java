package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.BankAccountRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.BankAccountResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.BankAccountEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.CustomerEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    // Entity -> Domain
    default BankAccount toDomain(
            BankAccountEntity entity
    ) {

        return BankAccount.restore(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getAccountNumber(),
                entity.getBalance(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    // Request -> Domain
    default BankAccount toDomain(
            BankAccountRequest request
    ) {

        return BankAccount.create(
                null,
                request.getCustomerId(),
                request.getAccountNumber(),
                request.getBalance()
        );
    }

    // Domain -> Entity
    default BankAccountEntity toEntity(
            BankAccount bankAccount,
            CustomerEntity customer
    ) {

        return new BankAccountEntity(
                bankAccount.getId(),
                customer,
                bankAccount.getAccountNumber(),
                bankAccount.getBalance(),
                bankAccount.getStatus(),
                bankAccount.getCreatedAt(),
                bankAccount.getUpdatedAt()
        );
    }

    // Domain to Response
    default BankAccountResponse toResponse(
            BankAccount bankAccount
    ) {

        return BankAccountResponse.builder()
                .id(bankAccount.getId())
                .customerId(bankAccount.getCustomerId())
                .accountNumber(bankAccount.getAccountNumber())
                .balance(bankAccount.getBalance())
                .status(bankAccount.getStatus())
                .build();
    }

    // List of BankAccountEntity -> List of BankAccount
    List<BankAccount> toDomainList(
            List<BankAccountEntity> entities
    );

    // List of Customer -> List of CustomerResponse
    List<BankAccountResponse> toResponseList(
            List<BankAccount> bankAccounts
    );
}
