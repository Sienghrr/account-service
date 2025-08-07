package com.sieng.bank.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailDTO {
    private CustomerDTO customer;
    private List<CardResponseDTO> cards;
    private List<LoanResponseDTO> loans;
}
