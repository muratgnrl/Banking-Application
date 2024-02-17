package com.bankapplication.authservice.controller;

import com.bankapplication.authservice.dto.AccountDto;
import com.bankapplication.authservice.response.AccountResponse;
import com.bankapplication.authservice.service.ımpl.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;

    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> addAccount(@RequestBody AccountDto dto) {
        AccountResponse create = accountService.createAccount(dto);
        return ResponseEntity.ok(create);
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Long id) {
        AccountDto getAccountById = accountService.getAccountById(id);
        return ResponseEntity.ok(getAccountById);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable(value = "id") Long id,
                                              @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto= accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable(value = "id") Long id,
                                               @RequestBody Map<String, Double> request){

        double amount =request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);

        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/getAllAccount")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos=accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(value = "id") Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }
}
