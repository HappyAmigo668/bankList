package com.potapov.bankList.service;

import com.potapov.bankList.connection.BankListJDBC;
import com.potapov.bankList.dao.AccountDao;
import com.potapov.bankList.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService extends BankListJDBC implements AccountDao {

    private Connection connection = getConnection();
    private PreparedStatement preparedStatement = null;
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

        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Account account = new Account();
                account.setAccountId(resultSet.getInt("ACCOUNTID"));
                account.setAccount(resultSet.getInt("ACCOUNT"));
                account.setUserId(resultSet.getInt("USERID"));

                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            userService.closingConnectionAndStatement();
        }

        return accountList;
    }

    @Override
    public Account getById(int accountId) {
        String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNTID = ?";
        Account account = new Account();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            account.setAccountId(resultSet.getInt("ACCOUNTID"));
            account.setAccount(resultSet.getInt("ACCOUNT"));
            account.setUserId(resultSet.getInt("USERID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            userService.closingConnectionAndStatement();
        }

        return account;
    }


    @Override
    public void update(Account account) {

        String sql = "UPDATE ACCOUNT SET ACCOUNT = ?, USERID = ? WHERE ACCOUNTID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.getAccount());
            preparedStatement.setInt(2, account.getUserId());
            preparedStatement.setInt(3, account.getAccountId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            userService.closingConnectionAndStatement();
        }
    }

    @Override
    public Boolean delete(int accountId) {
        return null;
    }


}
