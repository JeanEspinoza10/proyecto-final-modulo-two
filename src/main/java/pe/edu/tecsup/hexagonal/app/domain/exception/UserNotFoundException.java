package pe.edu.tecsup.hexagonal.app.domain.exception;


public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }

}