package com.bankapplication.authservice.service.ımpl;

import com.bankapplication.authservice.dto.AccountDto;
import com.bankapplication.authservice.entity.Account;
import com.bankapplication.authservice.mapper.AccountMapper;
import com.bankapplication.authservice.repository.AccountRepository;
import com.bankapplication.authservice.response.AccountResponse;
import com.bankapplication.authservice.response.Meta;
import com.bankapplication.authservice.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountResponse createAccount(AccountDto dto) {
        Account account = AccountMapper.mapToAccount(dto);
        account = accountRepository.save(account);
        if (account != null) {
            AccountResponse response = new AccountResponse();
            response.account = AccountMapper.mapToAccountDto(account);
            response.meta = new Meta(200, "Account created successfully!");
            return response;
        } else {
            throw new IllegalStateException("Failed to create account");
        }

    }
    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists "));
        return AccountMapper.mapToAccountDto(account);
    }
    @Override
    public AccountDto deposit(Long id,double amount){
        Account account =accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        double total = account.getBalance() +amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount){
        Account account =accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        if (account.getBalance() < amount){
            throw new RuntimeException("insufficient amount");

        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Override
    public List<AccountDto> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }
    public void deleteAccount(Long id){

        Account account=accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);
    }

}
