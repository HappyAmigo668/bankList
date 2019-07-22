package com.potapov.bankList.service;

import com.potapov.bankList.connection.BankListJDBC;
import com.potapov.bankList.dao.AccountDao;
import com.potapov.bankList.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AccountService extends BankListJDBC implements AccountDao {

    private Connection connection = getConnection();
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private UserService userService = new UserService();

    @Override
    public Boolean add(Account account) {
        String sql = "INSERT INTO ACCOUNT(ACCOUNTID, ACCOUNT, USERID) VALUES(?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.getAccountId());
            preparedStatement.setInt(2, account.getAccount());
            preparedStatement.setInt(3, account.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            userService.closingConnectionAndStatement();
        }
        return true;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account getById(int accountId) {
        return null;
    }

    @Override
    public Account getByUserId(int userId) {
        return null;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public Boolean delete(int accountId) {
        return null;
    }


}
