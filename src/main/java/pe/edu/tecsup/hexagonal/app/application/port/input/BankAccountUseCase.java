package pe.edu.tecsup.hexagonal.app.application.port.input;

import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountUseCase {
    BankAccount create(BankAccount bankAccount);
    BigDecimal getBalance(Long bankAccountId);
    List<BankAccount> findAll();

}
