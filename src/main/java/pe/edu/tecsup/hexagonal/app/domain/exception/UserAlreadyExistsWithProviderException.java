package pe.edu.tecsup.hexagonal.app.domain.exception;


public class UserAlreadyExistsWithProviderException extends RuntimeException{

    public UserAlreadyExistsWithProviderException(String message) {
        super(message);
    }

}