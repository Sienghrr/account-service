package com.sieng.bank.account.services;

import java.util.List;

import com.sieng.bank.account.entity.Account;

public interface AccountService {
	Account save(Account Account);
	List<Account> getAccounts();
	Account getById(Long id);
}
