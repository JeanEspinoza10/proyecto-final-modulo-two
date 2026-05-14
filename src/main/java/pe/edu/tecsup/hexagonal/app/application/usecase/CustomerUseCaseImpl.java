package pe.edu.tecsup.hexagonal.app.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.hexagonal.app.application.port.input.CustomerUseCase;
import pe.edu.tecsup.hexagonal.app.application.port.output.CustomerRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidCustomerException;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer create(Customer newCustomer) {
        if(newCustomer == null){
            throw new InvalidCustomerException("User cannot be null");
        }
        // Validate if exists Customer with Document
        if(customerRepositoryPort.existsByDocument(newCustomer.getDocument())){
            throw new InvalidCustomerException("Document already exists : " + newCustomer.getDocument());
        }
        // Validate if exists Customer with Email
        if(customerRepositoryPort.existsByEmail(newCustomer.getEmail())){
            throw new InvalidCustomerException("Email already exists : " + newCustomer.getEmail());
        }
        return customerRepositoryPort.save(newCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepositoryPort.findAll();
    }
}
