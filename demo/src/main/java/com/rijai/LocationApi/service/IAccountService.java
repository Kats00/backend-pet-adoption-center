package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts();
    Account addAccount(Account account);
    Account updateAccount(long id, Account account);
    Account getAccount(long id);
    Account deleteAccount(long id);
    Account authenticate(String username, String password);
}