package com.sieng.bank.account.functions;

import com.sieng.bank.account.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountFunctions {

    @Bean
    Consumer<Long> updateCustomerCommunication(CustomerService customerService){
        return customerId ->{
            customerService.updateCustomerCommunication(customerId);
            log.info("update customer communication with customerId {}",customerId);
        };
    }


}

