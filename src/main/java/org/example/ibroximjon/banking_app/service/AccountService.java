package org.example.ibroximjon.banking_app.service;

import org.example.ibroximjon.banking_app.dto.AccountDto;
import org.example.ibroximjon.banking_app.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(int id);
    AccountDto putDeposit(int id, double amount);
    AccountDto withdraw(int id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(int id);
}
