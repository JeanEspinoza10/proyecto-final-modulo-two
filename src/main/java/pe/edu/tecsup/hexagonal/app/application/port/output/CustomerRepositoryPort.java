package pe.edu.tecsup.hexagonal.app.application.port.output;

import pe.edu.tecsup.hexagonal.app.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    List<Customer> findAll();
}
