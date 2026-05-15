package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private String type;
    private String description;

}
