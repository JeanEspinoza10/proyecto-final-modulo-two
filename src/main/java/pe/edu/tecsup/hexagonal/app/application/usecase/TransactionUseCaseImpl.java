package pe.edu.tecsup.hexagonal.app.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.hexagonal.app.application.port.input.TransactionUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.output.BankAccountRepositoryPort;
import pe.edu.tecsup.hexagonal.app.application.port.output.CommissionCalculatorPort;
import pe.edu.tecsup.hexagonal.app.application.port.output.NotificationPort;
import pe.edu.tecsup.hexagonal.app.application.port.output.TransactionRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidBankAccounException;
import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;
import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionUseCaseImpl implements TransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final BankAccountRepositoryPort bankAccountRepositoryPort;
    private final CommissionCalculatorPort commissionCalculatorPort;
    private final NotificationPort notificationPort;

    @Override
    public Transaction create(Transaction transaction) {

        try {
            BigDecimal commission = commissionCalculatorPort.calculateTransferCommission(transaction.getAmount());
            transaction.updateCommission(commission);
            BankAccount sourceAccount = bankAccountRepositoryPort
                    .findById(transaction.getSourceAccountId())
                    .orElseThrow(() ->
                            new InvalidBankAccounException(
                                    "Source account not found"
                            )
                    );

            BankAccount destinationAccount = bankAccountRepositoryPort
                    .findById(transaction.getDestinationAccountId())
                    .orElseThrow(() ->
                            new InvalidBankAccounException(
                                    "Destination account not found"
                            )
                    );

            BigDecimal total =
                    transaction.getAmount()
                            .add(transaction.getCommission());

            sourceAccount.withdraw(total);
            destinationAccount.deposit(transaction.getAmount());

            bankAccountRepositoryPort.save(sourceAccount);
            bankAccountRepositoryPort.save(destinationAccount);
            transaction.complete();
            notificationPort.notifyTransfer("Transfer correct");

        } catch (Exception exception) {
            transaction.fail();
            notificationPort.notifyTransfer("Transfer incorrect");
        }
        return transactionRepositoryPort.save(transaction);
    }
}
