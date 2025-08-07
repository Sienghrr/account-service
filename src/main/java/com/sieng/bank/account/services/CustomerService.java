package com.sieng.bank.account.services;

import java.util.List;
import java.util.Optional;

import com.sieng.bank.account.entity.Customer;

import javax.swing.text.html.Option;

public interface CustomerService {
	Customer save(Customer customer);
	List<Customer> getCustomers();
	Customer getById(Long id);
}
