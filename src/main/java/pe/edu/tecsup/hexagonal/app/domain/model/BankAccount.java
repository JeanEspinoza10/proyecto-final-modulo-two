package pe.edu.tecsup.hexagonal.app.domain.model;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.hexagonal.app.domain.enums.BankAccountStatus;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidBankAccounException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class BankAccount {

    private final Long id;
    private final String customerId;
    private final String accountNumber;
    private BigDecimal balance;
    private final BankAccountStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private BankAccount(
            Long id,
            String customerId,
            String accountNumber,
            BigDecimal balance,
            BankAccountStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {

        validateBalance(balance);
        validateStatus(status);

        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BankAccount create(
            Long id,
            String customerId,
            String accountNumber,
            BigDecimal balance
    ) {

        return new BankAccount(
                id,
                customerId,
                accountNumber,
                balance,
                BankAccountStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }


    private void validateBalance(BigDecimal balance) {

        if (balance == null) {
            throw new InvalidBankAccounException("Balance cannot be null");
        }

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBankAccounException("Balance cannot be negative");
        }
    }

    private void validateStatus(BankAccountStatus status) {

        if (status == null) {
            throw new InvalidBankAccounException(
                    "Status cannot be null"
            );
        }
    }

    public void withdraw(BigDecimal amount) {

        if (amount == null) {
            throw new InvalidBankAccounException(
                    "Amount cannot be null"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBankAccounException(
                    "Amount must be greater than zero"
            );
        }

        if (this.balance.compareTo(amount) < 0) {
            throw new InvalidBankAccounException(
                    "Insufficient balance"
            );
        }

        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {

        if (amount == null) {
            throw new InvalidBankAccounException(
                    "Amount cannot be null"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBankAccounException(
                    "Amount must be greater than zero"
            );
        }

        this.balance = this.balance.add(amount);
    }

}
