package pe.edu.tecsup.hexagonal.app.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.hexagonal.app.application.port.input.BankAccountUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.output.BankAccountRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidBankAccounException;
import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class BankAccountUseCaseImpl implements BankAccountUseCase {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;

    @Override
    public BankAccount create(BankAccount bankAccount) {
        if(bankAccount == null){
            throw new InvalidBankAccounException("BankAccount cannot be null");
        }
        // Validate if exists AccountNumber
        if(bankAccountRepositoryPort.existsByAccountNumber(bankAccount.getAccountNumber())){
            throw new InvalidBankAccounException(  "Account number already exists: " + bankAccount.getAccountNumber());
        }
        return bankAccountRepositoryPort.save(bankAccount);
    }

    @Override
    public BigDecimal getBalance(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepositoryPort
                .findById(bankAccountId)
                .orElseThrow(() ->
                        new InvalidBankAccounException(
                                "Bank account not found with id: " + bankAccountId
                        )
                );
        return bankAccount.getBalance();
    }
}
