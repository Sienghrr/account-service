package com.sieng.bank.account.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sieng.bank.account.entity.Account;
import com.sieng.bank.account.repository.AccountRepository;
import com.sieng.bank.account.services.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	private final AccountRepository AccountRepository;

	@Override
	public Account save(Account Account) {		
		return AccountRepository.save(Account);
	}

	@Override
	public List<Account> getAccounts() {
		return AccountRepository.findAll();
	}

	@Override
	public Account getById(Long id) {
		return AccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
	}

	
}
