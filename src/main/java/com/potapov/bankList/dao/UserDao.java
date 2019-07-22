package com.potapov.bankList.dao;

import com.potapov.bankList.entity.User;

import java.util.List;

public interface UserDao {

    Boolean add(User user);

    List<User> getAll();

    User getById(int userId);

    void update(User user);

    Boolean delete(int userId);

}
