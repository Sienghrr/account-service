package com.sieng.bank.account.services.Impl;

import java.util.List;
import java.util.Optional;

import com.sieng.bank.account.dto.CustomerMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.sieng.bank.account.entity.Customer;
import com.sieng.bank.account.repository.CustomerRepository;
import com.sieng.bank.account.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	private final CustomerRepository customerRepository;
	private final StreamBridge streamBridge;
	@Override
	public Customer save(Customer customer) {		
		customer = customerRepository.save(customer);
		sendCommunication(customer);
		return  customer;
	}
	private void sendCommunication(Customer customer){
		CustomerMessageDTO customerMessageDTO = new CustomerMessageDTO
				(customer.getCustomerId(),customer.getName()
						,customer.getEmail(),customer.getMobileNumber());
		log.info("sending communication rq for the details:{}",customerMessageDTO);
		var result = streamBridge.send("sendCommunication-out-0",customerMessageDTO);
		log.info("Is the communication rq successfully triggered?: {}",result);
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
	}

	@Override
	public void updateCustomerCommunication(Long id) {
		Customer customer = getById(id);
		customer.setCommunicationAlreadySent(true);
		customerRepository.save(customer);
	}


}
