package com.potapov.bankList.service;

import com.potapov.bankList.entity.User;
import com.potapov.bankList.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    User getById(int userId) throws Exception {

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

    List<User> getAll(){
        return userRepository.getAll();
    }

    User add(User user){

        if (userRepository.getById(user.getUserId()) == null){
            userRepository.add(user);
        } else {
            throw new RuntimeException("User is already exists.");
        }
        return user;
    }

    User update(User user){

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


    boolean delete(int userId){

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
