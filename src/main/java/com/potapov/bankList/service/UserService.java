package com.potapov.bankList.service;

import com.potapov.bankList.entity.Account;
import com.potapov.bankList.entity.User;
import com.potapov.bankList.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private AccountService accountService;

    public User getRichestUser() throws Exception {
        List<Account> accountList = accountService.getAll();

        Account maxAccount = new Account();
        maxAccount.setAccount(0);

        for (Account account : accountList) {
            if (account.getAccount() > maxAccount.getAccount()) {
                maxAccount = account;
            }
        }


        if (maxAccount.getAccount() > 0) {
            return getById(maxAccount.getUserId());
        }

        return null;
    }

    public User getById(int userId) throws Exception {

        if (userId >= 0) {

            User user = userRepository.getById(userId);

            if (user != null) {
                return user;

            } else {
                throw new ResourceNotFoundException("User with id: " + userId + " is not found.");
            }

        } else {
            throw new RuntimeException("Id is not valid.");
        }

    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User add(User user){

        if (userRepository.getById(user.getUserId()) == null){
            userRepository.add(user);
        } else {
            throw new RuntimeException("User is already exists.");
        }
        return user;
    }

    public User update(User user){

        try {

            User userInstance = getById(user.getUserId());

            if (userInstance != null) {
                userRepository.update(user);

            } else {
                throw new ResourceNotFoundException("User is not found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


    public boolean delete(int userId){

        if (userId >= 0) {

            User user = userRepository.getById(userId);

            if (user != null) {
                userRepository.delete(userId);

            } else {
                throw new ResourceNotFoundException("User is not found.");
            }

        } else {
            throw new RuntimeException("Id is not valid.");

        }

        return true;
    }

}
