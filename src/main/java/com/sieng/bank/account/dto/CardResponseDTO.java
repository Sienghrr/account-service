package com.sieng.bank.account.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardResponseDTO {
    private Long cardId;
    private Long customerId;
    private String cardNumber;
    private String cardType;
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal availableAmount;
    private LocalDate createDate;
}
