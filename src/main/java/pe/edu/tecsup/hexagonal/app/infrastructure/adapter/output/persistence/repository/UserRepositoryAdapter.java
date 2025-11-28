package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hexagonal.app.application.port.output.UserRepositoryPort;
import pe.edu.tecsup.hexagonal.app.domain.model.User;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.UserEntity;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

// Adapter implementation
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {

        log.info("Saving user: {}", user);

        UserEntity entity = this.mapper.toEntity(user);
        UserEntity savedEntity = this.jpaRepository.save(entity);
        User savedUser = this.mapper.toDomain(savedEntity);

        log.info("Saved user via Spring Data: {}", savedUser);

        return savedUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("Finding user by ID: {}", id);

        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        log.info("Finding all users");
        List<UserEntity> entities = this.jpaRepository.findAll();
        return this.mapper.toDomain(entities);
    }

    @Override
    public List<User> findByNameContaining(String name) {
        log.info("Finding users by name containing: {}", name);
        List<UserEntity> entities = this.jpaRepository.findByNameContainingIgnoreCase(name);
        return this.mapper.toDomain(entities);
    }

    @Override
    public void deleteById(Long id) {
        this.jpaRepository.deleteById(id);

    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking if email exists: {}", email);
        return this.jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return this.jpaRepository.existsById(id);
    }


}
