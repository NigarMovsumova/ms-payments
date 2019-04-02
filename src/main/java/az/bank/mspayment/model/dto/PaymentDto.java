package az.bank.mspayment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String customerId;
    private String accountId;
    private LocalDateTime createdAt;
    private Boolean increased;
    private BigDecimal amount;
    private String currency;
    private String category;
    private String description;
}
