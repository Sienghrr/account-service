package com.sieng.bank.account.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.sieng.bank.account.dto.CustomerDTO;
import com.sieng.bank.account.entity.Customer;

@Component
public class CustomerMapper {
	
	public Customer toCustomer(CustomerDTO dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setMobileNumber(dto.getMobileNumber());
		customer.setCreateDate(LocalDate.parse(dto.getCreateDate()));
		return customer;
	}
	public CustomerDTO toCustomerDTO(Customer entity) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName(entity.getName());
		customerDTO.setEmail(entity.getEmail());
		customerDTO.setMobileNumber(entity.getMobileNumber());
		customerDTO.setCreateDate(
				entity.getCreateDate() != null? entity.getCreateDate().toString(): ""
		);
		return customerDTO;
	}
}
