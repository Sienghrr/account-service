package com.sieng.bank.account.services.client;

import com.sieng.bank.account.dto.CardResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "card")
public interface CardFeignClient {
    @GetMapping("/api/cards/{customerId}")
    List<CardResponseDTO> getCardInfo(@PathVariable Long customerId);
}
