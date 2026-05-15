package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.hexagonal.app.application.port.input.TransactionUseCase;
import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponseFactory;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.TransactionRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.TransactionResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.TransactionMapper;

@RestController
@RequestMapping("/api/transfer-money")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionUseCase transactionUseCase;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransfer(@RequestBody TransactionRequest transactionRequest){
        Transaction transaction = this.transactionMapper.toDomain(transactionRequest);
        Transaction createdTransaction = this.transactionUseCase.create(transaction);
        TransactionResponse transactionResponse = this.transactionMapper.toResponse(createdTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseFactory.success(
                        "Transfer successfully",
                        HttpStatus.CREATED.value(),
                        transactionResponse
                )
        );
    }
}
