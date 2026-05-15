package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidBankAccounException;
import pe.edu.tecsup.hexagonal.app.domain.exception.InvalidCustomerException;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponseFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCustomer(
            InvalidCustomerException ex
    ) {

        return ResponseEntity.badRequest()
                .body(
                        ApiResponseFactory.error(
                                ex.getMessage(),
                                HttpStatus.BAD_REQUEST.value()
                        )
                );
    }

    @ExceptionHandler(InvalidBankAccounException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidBankAccount(
            InvalidBankAccounException ex
    ) {

        return ResponseEntity.badRequest()
                .body(
                        ApiResponseFactory.error(
                                ex.getMessage(),
                                HttpStatus.BAD_REQUEST.value()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(
            Exception ex
    ) {

        return ResponseEntity.badRequest()
                .body(
                        ApiResponseFactory.error(
                                ex.getMessage(),
                                HttpStatus.BAD_REQUEST.value()
                        )
                );
    }

}
