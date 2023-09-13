package ru.sanctio.dao;

import jakarta.ejb.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class DBManagerUserImpl implements DBManagerUser {

    @Override
    public boolean checkUser(String login, String password) {
        String SQL_QUERY = "SELECT * FROM users WHERE login = ? and password = ?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_QUERY)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
