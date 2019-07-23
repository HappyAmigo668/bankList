package com.potapov.bankList.service;

import com.potapov.bankList.entity.Account;
import com.potapov.bankList.repository.AccountRepository;
import com.potapov.bankList.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;

public class AccountService  {

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    Account getById(int accountId) throws Exception {

        if (accountId >= 0) {

            Account user = accountRepository.getById(accountId);

            if (user != null) {
                return user;

            } else {
                throw new ResourceNotFoundException("Account with id: " + accountId + " is not found.");
            }

        } else {
            throw new RuntimeException("Id is not valid.");
        }

    }


    List<Account> getAll(){
        return accountRepository.getAll();
    }


    Account add(Account account){

        if(userRepository.getById(account.getUserId()) != null){

            if (accountRepository.getById(account.getAccountId()) == null){

                accountRepository.add(account);

            } else {

                throw new RuntimeException("Account is already exists.");
            }
        } else {

            throw new ResourceNotFoundException("User is not found");

        }

        return account;
    }

    Account update(Account account){

        try {

            Account accountInstance = getById(account.getUserId());

            if (accountInstance != null) {

                accountRepository.update(account);

            } else {

                throw new ResourceNotFoundException("Account is not found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    boolean delete(int accountId){

        if (accountId >= 0) {

            Account account = accountRepository.getById(accountId);

            if (account != null) {
                accountRepository.delete(accountId);

            } else {
                throw new ResourceNotFoundException("Account is not found.");
            }

        } else {
            throw new RuntimeException("Id is not valid.");

        }

        return true;
    }

}
