package pe.edu.tecsup.hexagonal.app.domain.exception;

public class InvalidCustomerException extends RuntimeException {

    public InvalidCustomerException(String message) {
        super("Invalid: " + message);
    }
}
