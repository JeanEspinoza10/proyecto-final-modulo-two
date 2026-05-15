package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.commission;

import org.springframework.stereotype.Component;
import pe.edu.tecsup.hexagonal.app.application.port.output.CommissionCalculatorPort;

import java.math.BigDecimal;

@Component
public class CommissionAdapter implements CommissionCalculatorPort {
    @Override
    public BigDecimal calculateTransferCommission(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        return amount.multiply(new BigDecimal("0.02"));
    }
}
