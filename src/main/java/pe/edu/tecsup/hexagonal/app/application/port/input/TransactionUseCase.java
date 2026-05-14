package pe.edu.tecsup.hexagonal.app.application.port.input;

import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;

public interface TransactionUseCase {
    Transaction create(Transaction transaction);
}
