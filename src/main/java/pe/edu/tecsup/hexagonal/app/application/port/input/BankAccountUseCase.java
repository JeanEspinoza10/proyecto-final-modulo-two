package pe.edu.tecsup.hexagonal.app.application.port.input;

import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;

import java.math.BigDecimal;

public interface BankAccountUseCase {
    BankAccount create(BankAccount bankAccount);
    BigDecimal getBalance(Long bankAccountId);
}
