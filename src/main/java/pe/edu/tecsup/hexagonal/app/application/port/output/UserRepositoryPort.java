package pe.edu.tecsup.hexagonal.app.application.port.output;


import pe.edu.tecsup.hexagonal.app.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    //
    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    List<User> findByNameContaining(String name);

    void deleteById(Long id);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

}
