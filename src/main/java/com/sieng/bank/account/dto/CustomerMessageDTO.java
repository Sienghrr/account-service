package com.sieng.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerMessageDTO {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}
