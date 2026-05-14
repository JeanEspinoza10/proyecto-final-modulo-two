package pe.edu.tecsup.hexagonal.app.domain.model;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.hexagonal.app.domain.enums.TransactionStatus;
import pe.edu.tecsup.hexagonal.app.domain.enums.TransactionType;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidTransactionException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Transaction {

    private final Long id;

    private final Long sourceAccountId;

    private final Long destinationAccountId;

    private final BigDecimal amount;

    private BigDecimal commission;

    private final TransactionType type;

    private TransactionStatus status;

    private final String description;

    private final LocalDateTime createdAt;

    private Transaction(
            Long id,
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            BigDecimal commission,
            TransactionType type,
            TransactionStatus status,
            String description,
            LocalDateTime createdAt
    ) {

        validateAccounts(sourceAccountId, destinationAccountId);
        validateAmount(amount);
        validateType(type);
        validateStatus(status);

        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.commission = commission;
        this.type = type;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Transaction createTransfer(
            Long id,
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            BigDecimal commission,
            String description
    ) {

        return new Transaction(
                id,
                sourceAccountId,
                destinationAccountId,
                amount,
                commission,
                TransactionType.TRANSFERENCIA,
                TransactionStatus.PENDIENTE,
                description,
                LocalDateTime.now()
        );
    }

    public void complete() {
        this.status = TransactionStatus.COMPLETADA;
    }

    public void fail() {
        this.status = TransactionStatus.FALLIDA;
    }

    public void cancel() {
        this.status = TransactionStatus.CANCELADA;
    }

    private void validateAccounts(
            Long sourceAccountId,
            Long destinationAccountId
    ) {

        if (sourceAccountId == null) {
            throw new InvalidTransactionException(
                    "Source account cannot be empty"
            );
        }

        if (destinationAccountId == null ) {
            throw new InvalidTransactionException(
                    "Destination account cannot be empty"
            );
        }

        if (sourceAccountId.equals(destinationAccountId)) {
            throw new InvalidTransactionException(
                    "Source and destination accounts cannot be the same"
            );
        }
    }

    public void updateCommission(BigDecimal commission){
        validateCommission(commission);
        this.commission = commission;
    }

    private void validateAmount(BigDecimal amount) {

        if (amount == null) {
            throw new InvalidTransactionException(
                    "Amount cannot be null"
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException(
                    "Amount must be greater than zero"
            );
        }
    }

    private void validateCommission(BigDecimal commission) {

        if (commission == null) {
            throw new InvalidTransactionException(
                    "Commission cannot be null"
            );
        }

        if (commission.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidTransactionException(
                    "Commission cannot be negative"
            );
        }
    }

    private void validateType(TransactionType type) {

        if (type == null) {
            throw new InvalidTransactionException(
                    "Transaction type cannot be null"
            );
        }
    }

    private void validateStatus(TransactionStatus status) {

        if (status == null) {
            throw new InvalidTransactionException(
                    "Transaction status cannot be null"
            );
        }
    }
}
