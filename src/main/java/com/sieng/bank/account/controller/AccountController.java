package com.sieng.bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sieng.bank.account.dto.AccountDTO;
import com.sieng.bank.account.entity.Account;
import com.sieng.bank.account.mapper.AccountMapper;
import com.sieng.bank.account.services.AccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService AccountService;
	@Autowired
	private AccountMapper AccountMapper;
	
	@PostMapping
	public ResponseEntity<?> saveAccount(@RequestBody AccountDTO dto){
		Account Account = AccountMapper.toAccount(dto);
		Account = AccountService.save(Account);
		return ResponseEntity.ok(Account);
	}
	
	@GetMapping
	public ResponseEntity<?> getAccounts(){
		return ResponseEntity.ok(AccountService.getAccounts());
	}
	@GetMapping("{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
		return ResponseEntity.ok(AccountService.getById(accountId));
	}

}
