package com.sieng.bank.account.config;

import com.sieng.bank.account.entity.Account;
import com.sieng.bank.account.entity.Customer;
import com.sieng.bank.account.repository.AccountRepository;
import com.sieng.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Slf4j
@RequiredArgsConstructor
@Component
public class SetupAccountRunner implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setName("Sona");
        customer.setEmail("sona@gmail.com");
        customer.setMobileNumber("012345678");
        customer.setCreateDate(LocalDate.now());
        customerRepository.save(customer);

        Account account = new Account();
        account.setAccountNumber(122666l);
        account.setAccountType("Trading");
        account.setCreateDate(LocalDate.now());
        account.setCustomerId(customer);
        account.setBranchAddress("BMC");
        accountRepository.save(account);
        log.info("Account Created");
    }
}
