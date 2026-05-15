package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hexagonal.app.application.port.output.BankAccountRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.BankAccountEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.CustomerEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.BankAccountMapper;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BankAccountRepositoryAdapter implements BankAccountRepositoryPort {

    private final BankAccountJpaRepository bankAccountJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final BankAccountMapper bankAccountMapper;
    private final CustomerMapper customerMapper;

    @Override
    public BankAccount save(BankAccount bankAccount, Customer customer) {
        CustomerEntity customerEntity = this.customerMapper.toEntity(customer);
        BankAccountEntity bankAccountEntity =
                this.bankAccountMapper.toEntity(
                        bankAccount,
                        customerEntity
                );

        BankAccountEntity savedEntity = this.bankAccountJpaRepository.save(bankAccountEntity);

        return this.bankAccountMapper.toDomain(savedEntity);
    }

    @Override
    public void update(BankAccount bankAccount) {
        Optional<CustomerEntity> customerEntity =  this.customerJpaRepository.findById(bankAccount.getCustomerId());

        BankAccountEntity bankAccountEntity =
                this.bankAccountMapper.toEntity(
                        bankAccount,
                        customerEntity.get()
                );

        BankAccountEntity savedEntity = this.bankAccountJpaRepository.save(bankAccountEntity);

        this.bankAccountMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return this.bankAccountJpaRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return this.bankAccountJpaRepository
                .findById(id)
                .map(this.bankAccountMapper::toDomain);
    }

    @Override
    public List<BankAccount> findAll() {
        List<BankAccountEntity> foundBankAccountEntity  = this.bankAccountJpaRepository.findAll();
        return this.bankAccountMapper.toDomainList(foundBankAccountEntity);
    }

}
