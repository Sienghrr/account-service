package com.sieng.bank.account.services;

import java.util.List;

import com.sieng.bank.account.entity.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	List<Customer> getCustomers();
	Customer getById(Long id);
}
