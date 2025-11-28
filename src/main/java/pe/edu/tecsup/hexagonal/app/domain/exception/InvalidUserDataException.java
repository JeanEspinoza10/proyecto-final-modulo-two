package pe.edu.tecsup.hexagonal.app.domain.exception;


public class InvalidUserDataException extends RuntimeException{

    public InvalidUserDataException(String message) {
        super("Invalid user data: " + message);
    }

}