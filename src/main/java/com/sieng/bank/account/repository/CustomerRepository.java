package com.sieng.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sieng.bank.account.entity.Customer;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
