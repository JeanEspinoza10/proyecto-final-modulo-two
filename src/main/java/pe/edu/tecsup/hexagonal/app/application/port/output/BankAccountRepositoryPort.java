package pe.edu.tecsup.hexagonal.app.application.port.output;

import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;

import java.util.Optional;

public interface BankAccountRepositoryPort {
    BankAccount save(BankAccount bankAccount);
    boolean existsByAccountNumber(String accountNumber);
    Optional<BankAccount> findById(Long id);
}
