package pe.edu.tecsup.hexagonal.app.application.port.input;


import pe.edu.tecsup.hexagonal.app.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(Long id, User user);

}
