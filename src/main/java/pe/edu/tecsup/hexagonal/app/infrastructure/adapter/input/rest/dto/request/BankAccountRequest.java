package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.hexagonal.app.domain.enums.BankAccountStatus;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequest {
    private Long customerId;
    private String accountNumber;
    private BigDecimal balance;
    private BankAccountStatus status;
}

