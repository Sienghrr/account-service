package com.sieng.bank.account.controller;

import com.sieng.bank.account.dto.*;
import com.sieng.bank.account.services.client.CardFeignClient;
import com.sieng.bank.account.services.client.LoanFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sieng.bank.account.entity.Customer;
import com.sieng.bank.account.mapper.CustomerMapper;
import com.sieng.bank.account.services.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CardFeignClient cardFeignClient;
	@Autowired
	private LoanFeignClient loanFeignClient;
	
	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO dto){
		Customer customer = customerMapper.toCustomer(dto);
		customer = customerService.save(customer);
		return ResponseEntity.ok(customer);
	}
	@GetMapping
	public ResponseEntity<?> getCustomers(){
		return ResponseEntity.ok(customerService.getCustomers());
	}
	@GetMapping("{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
		return ResponseEntity.ok(customerService.getById(customerId));
	}

	//@CircuitBreaker(name = "customerDetailSupport",fallbackMethod = "getCustomerDetailDefault")
	//@Retry(name = "retryCustomerDetail",fallbackMethod = "getCustomerDetailDefault")
	@GetMapping("customerDetail/{customerId}")
	public ResponseEntity<CustomerDetailDTO> getCustomerDetail(
			@RequestHeader("siengbank-correlation-id") String correlationId, @PathVariable Long customerId){
		//log.debug("Correlation id found {}",correlationId);
		log.debug("fetch getCustomerDetail start");
		CustomerDetailDTO dto = new CustomerDetailDTO();
		Customer customer = customerService.getById(customerId);
		if(customer == null){
			throw new RuntimeException("No customer found with this id");
		}
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		List<LoanResponseDTO> loanInfo = loanFeignClient.getLoanInfo(correlationId, customerId);
		List<CardResponseDTO> cardInfo = cardFeignClient.getCardInfo(correlationId, customerId);
		dto.setCustomer(customerDTO);
		dto.setCards(cardInfo);
		dto.setLoans(loanInfo);
		log.debug("fetch getCustomerDetail end");
		return ResponseEntity.ok(dto);
	}

	public ResponseEntity<CustomerDetailDTO> getCustomerDetailDefault(@PathVariable Long customerId,Throwable e){
		CustomerDetailDTO dto = new CustomerDetailDTO();
		Customer customer = customerService.getById(customerId);
		if(customer == null){
			throw new RuntimeException("No customer found with this id");
		}
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		dto.setCustomer(customerDTO);
		return ResponseEntity.ok(dto);
	}


}
