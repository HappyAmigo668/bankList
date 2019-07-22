package com.potapov.bankList.service;

import com.potapov.bankList.connection.BankListJDBC;
import com.potapov.bankList.dao.UserDao;
import com.potapov.bankList.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends BankListJDBC implements UserDao {

    private Connection connection = getConnection();
    private PreparedStatement preparedStatement = null;

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
            closingConnectionAndStatement();
        }

        return true;
    }

    @Override
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USER";

        try {
            Statement statement = connection.createStatement();
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
            closingConnectionAndStatement();
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
            closingConnectionAndStatement();
        }

        return user;
    }


    @Override
    public void update(User user) {

        String sql = "UPDATE USER SET NAME = ?, SURENAME = ? WHERE USERID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSureName());
            preparedStatement.setInt(3, user.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closingConnectionAndStatement();
        }
    }

    @Override
    public Boolean delete(int userId) {
        String sql = "DELETE FROM USER WHERE USERID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closingConnectionAndStatement();
        }
        return true;
    }


    public void closingConnectionAndStatement() {
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
