package com.potapov.bankList.service;

import com.potapov.bankList.connection.BankListJDBC;
import com.potapov.bankList.dao.UserDao;
import com.potapov.bankList.entity.User;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends BankListJDBC implements UserDao {

    private Connection connection = getConnection();
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    @Override
    public Boolean add(User user) {
        String sql = "INSERT INTO USER(USERID, NAME, SURENAME) VALUES(?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSureName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closingConnectionandStatement();
        }

        return true;
    }

    @Override
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USER";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setSureName(resultSet.getString("SURENAME"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closingConnectionandStatement();
        }

        return userList;
    }



    @Override
    public User getById(int userId) {

        String sql = "SELECT * FROM USER WHERE USERID = ?";
        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            user.setUserId(resultSet.getInt("USERID"));
            user.setName(resultSet.getString("NAME"));
            user.setSureName(resultSet.getString("SURENAME"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closingConnectionandStatement();
        }

        return user;
    }

    @Override
    public User update(User user) {

        return null;
    }

    @Override
    public Boolean delete(int userId) {
        return null;
    }

    private void closingConnectionandStatement() {
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
