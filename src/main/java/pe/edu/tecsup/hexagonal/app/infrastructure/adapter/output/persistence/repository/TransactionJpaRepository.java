package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.TransactionEntity;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

}
