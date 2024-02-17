package com.bankapplication.authservice.service;

import com.bankapplication.authservice.dto.AccountDto;
import com.bankapplication.authservice.response.AccountResponse;

import java.util.List;


public interface AccountService {
    AccountResponse createAccount(AccountDto dto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
}
