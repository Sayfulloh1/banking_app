package org.example.ibroximjon.banking_app.controller;


import org.example.ibroximjon.banking_app.dto.AccountDto;
import org.example.ibroximjon.banking_app.entity.Account;
import org.example.ibroximjon.banking_app.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") int id) {
        AccountDto accountDto = accountService.getAccount(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(
          @PathVariable  Integer id,
         @RequestBody  Map<String, Double> request
    ) {
        Double amount = request.get("amount");
       AccountDto accountDto =  accountService.putDeposit(id,amount);
    return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable Integer id, @RequestBody  Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto =  accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
         accountService.deleteAccount(id);
         return ResponseEntity.ok("Account deleted");
    }
}
