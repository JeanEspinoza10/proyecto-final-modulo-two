package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.CustomerEntity;

import java.util.Optional;

interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByDocument(String document);
}
