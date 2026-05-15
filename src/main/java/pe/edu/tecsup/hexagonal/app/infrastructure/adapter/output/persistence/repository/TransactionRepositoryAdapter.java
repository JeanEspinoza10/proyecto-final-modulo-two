package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hexagonal.app.application.port.output.TransactionRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.TransactionEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.TransactionMapper;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionMapper transactionMapper;
    private final TransactionJpaRepository transactionJpaRepository;
    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = this.transactionMapper.toEntity(transaction);
        TransactionEntity recordTransaction = this.transactionJpaRepository.save(transactionEntity);
        return this.transactionMapper.toDomain(recordTransaction);
    }
}
