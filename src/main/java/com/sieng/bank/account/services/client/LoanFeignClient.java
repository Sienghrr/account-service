package com.sieng.bank.account.services.client;

import com.sieng.bank.account.dto.LoanResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "loan")
public interface LoanFeignClient {
    @GetMapping("/api/loans/{customerId}")
    List<LoanResponseDTO> getLoanInfo(@PathVariable Long customerId);
}
