package pe.edu.tecsup.hexagonal.app.application.port.input;

import pe.edu.tecsup.hexagonal.app.domain.model.Customer;

import java.util.List;

public interface CustomerUseCase {
    Customer create(Customer newCustomer);
    List<Customer> findAll();
}
