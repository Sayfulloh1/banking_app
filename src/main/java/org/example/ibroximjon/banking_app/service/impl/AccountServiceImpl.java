package org.example.ibroximjon.banking_app.service.impl;

import org.example.ibroximjon.banking_app.dto.AccountDto;
import org.example.ibroximjon.banking_app.entity.Account;
import org.example.ibroximjon.banking_app.mapper.AccountMapper;
import org.example.ibroximjon.banking_app.repository.AccountRepository;
import org.example.ibroximjon.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
     Account account = AccountMapper.mapToAccount(accountDto);
     Account savedAccount = accountRepository.save(account);
     return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccount(int id) {
       return  AccountMapper.mapToAccountDto(
               accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"))
       );
    }

    @Override
    public AccountDto putDeposit(int id, double amount) {
       Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double total = amount + account.getBalance();
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(int id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount > account.getBalance()) {
            throw new RuntimeException("Insufficient balance");
        }

        double total =  account.getBalance()-amount;
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return  accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(int id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }


}
