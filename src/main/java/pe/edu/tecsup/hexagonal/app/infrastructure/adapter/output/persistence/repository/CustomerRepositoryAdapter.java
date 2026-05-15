package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hexagonal.app.application.port.output.CustomerRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.CustomerEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = this.customerMapper.toEntity(customer);
        CustomerEntity recordCustomer = this.customerJpaRepository.save(customerEntity);
        return this.customerMapper.toDomain(recordCustomer);
    }

    @Override
    public boolean existsByDocument(String document) {
        return this.customerJpaRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.customerJpaRepository.existsByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerEntity> foundCustomerEntity =  this.customerJpaRepository.findAll();
        return this.customerMapper.toDomainList(foundCustomerEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<CustomerEntity> customerEntity = this.customerJpaRepository.findById(id);
        return this.customerMapper.toDomainOptional(customerEntity);
    }
}
