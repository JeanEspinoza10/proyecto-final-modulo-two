package pe.edu.tecsup.hexagonal.app.domain.exception;

public class InvalidBankAccounException extends RuntimeException {
    public InvalidBankAccounException(String message) {
        super(message);
    }
}
