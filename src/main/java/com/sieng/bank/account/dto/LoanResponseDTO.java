package com.sieng.bank.account.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanResponseDTO {
    private long loanNumber;
    private long customerId;
    private LocalDate startDate;
    private String loanType;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private LocalDate createDate;
}
