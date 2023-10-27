package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Account;
import com.rijai.LocationApi.model.Admin;
import com.rijai.LocationApi.model.User;
import com.rijai.LocationApi.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public List<Account> getAccounts() {
        return (List<Account>) repository.findAll();
    }

    @Override
    public Account getAccount(long id) {
        Optional optional=repository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (Account) optional.get();
    }

    @Override
    public Account addAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account updateAccount(long id, Account updatedAccount) {
        Optional<Account> existingOptional = repository.findById(id);
    
        if (existingOptional.isPresent()) {
            Account existing = existingOptional.get();
            existing.setUsername(updatedAccount.getUsername());
            existing.setPassword(updatedAccount.getPassword());
            existing.setEmail(updatedAccount.getEmail());
            existing.setCellphoneNum(updatedAccount.getCellphoneNum());

            if (updatedAccount instanceof User) {
                User updatedUser = (User) updatedAccount;
                ((User) existing).setFirstName(updatedUser.getFirstName());
                ((User) existing).setLastName(updatedUser.getLastName());
            } else if (updatedAccount instanceof Admin) {
                Admin updatedAdmin = (Admin) updatedAccount;
                ((Admin) existing).setDepartment(updatedAdmin.getDepartment());
            }

            return repository.save(existing);
        } else {
            System.out.println("Error updating account with id: " + id);
            return null;
        }
    }
    
    @Override
    public Account deleteAccount(long id)
    {
        Optional<Account> account = repository.findById(id);
        if(account.isPresent()) {
            repository.delete(account.get());
            System.out.println("Successfully deleted account: " +  id);
            return null;
        } else {
            System.out.println("No such account with id: " +  id);
            return null;
        }
    }

    @Override
    public Account authenticate(String username, String password) {
        // Assuming you have a repository method to find account by username
        Account account = repository.findByUsername(username);
    
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
    
        return null;
    }

}
