package com.potapov.bankList.dao;

import com.potapov.bankList.entity.Account;

import java.util.List;

public interface AccountDao {

    Boolean add(Account account);

    List<Account> getAll();

    Account getById(int accountId);

    void update(Account account);

    Boolean delete(int accountId);


}
