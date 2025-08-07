package com.sieng.bank.account.controller;

import com.sieng.bank.account.dto.CardResponseDTO;
import com.sieng.bank.account.dto.CustomerDetailDTO;
import com.sieng.bank.account.dto.LoanResponseDTO;
import com.sieng.bank.account.services.client.CardFeignClient;
import com.sieng.bank.account.services.client.LoanFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sieng.bank.account.dto.CustomerDTO;
import com.sieng.bank.account.entity.Customer;
import com.sieng.bank.account.mapper.CustomerMapper;
import com.sieng.bank.account.services.CustomerService;

import java.util.List;

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
	@Retry(name = "retryCustomerDetail",fallbackMethod = "getCustomerDetailDefault")
	@GetMapping("customerDetail/{customerId}")
	public ResponseEntity<CustomerDetailDTO> getCustomerDetail(@PathVariable Long customerId){
		System.out.println("----------Account Service Retry---------------");
		CustomerDetailDTO dto = new CustomerDetailDTO();
		Customer customer = customerService.getById(customerId);
		if(customer == null){
			throw new RuntimeException("No customer found with this id");
		}
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		List<LoanResponseDTO> loanInfo = loanFeignClient.getLoanInfo(customerId);
		List<CardResponseDTO> cardInfo = cardFeignClient.getCardInfo(customerId);
		dto.setCustomer(customerDTO);
		dto.setCards(cardInfo);
		dto.setLoans(loanInfo);
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

	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHelloLimiter", fallbackMethod = "sayHi")
	public String sayHello() {
		return "Hello, welcome to SiengBank";
	}
	public String sayHi(Throwable t) {
		return "Hi, welcome to SiengBank";
	}

}
