package pe.edu.tecsup.hexagonal.app.application.port.output;

import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
}
