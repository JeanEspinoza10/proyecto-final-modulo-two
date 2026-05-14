package pe.edu.tecsup.hexagonal.app.application.port.output;

import java.math.BigDecimal;

public interface CommissionCalculatorPort {
    BigDecimal calculateTransferCommission(BigDecimal amount);
}
