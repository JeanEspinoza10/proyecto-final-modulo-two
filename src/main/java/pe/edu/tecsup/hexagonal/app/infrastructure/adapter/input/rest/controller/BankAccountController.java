package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.hexagonal.app.application.port.input.BankAccountUseCase;
import pe.edu.tecsup.hexagonal.app.domain.model.BankAccount;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponseFactory;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.BankAccountRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.BalanceResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.BankAccountResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.BankAccountMapper;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class BankAccountController {
    private final BankAccountUseCase bankAccountUseCase;
    private final BankAccountMapper bankAccountMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<BankAccountResponse>> createBankAccount(@RequestBody BankAccountRequest bankAccountRequest){
        BankAccount bankAccount = this.bankAccountMapper.toDomain(bankAccountRequest);
        BankAccount createBankAccount = this.bankAccountUseCase.create(bankAccount);
        BankAccountResponse bankAccountResponse = this.bankAccountMapper.toResponse(createBankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseFactory.success(
                        "Bank Account successfully",
                        HttpStatus.CREATED.value(),
                        bankAccountResponse
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BankAccountResponse>>> findAllCustomer(){
        List<BankAccount> foundCustomer = this.bankAccountUseCase.findAll();
        return ResponseEntity.ok().body(ApiResponseFactory.success(
                "Bank Accounts found",
                200,
                this.bankAccountMapper.toResponseList(foundCustomer)
        ));
    }

    @GetMapping("/balance")
    public ResponseEntity<ApiResponse<List<BalanceResponse>>> getBalance(@RequestParam Long bankAccountId){
        BigDecimal balance =
                this.bankAccountUseCase.getBalance(bankAccountId);

        List<BalanceResponse> data = List.of(
                BalanceResponse.builder()
                        .balance(balance)
                        .build()
        );

        return ResponseEntity.ok().body(
                ApiResponseFactory.success(
                        "Balance found",
                        200,
                        data
                )
        );
    }
}
