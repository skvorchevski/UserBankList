package com.gmail.askvorchevski.repository.impl;

import com.gmail.askvorchevski.repository.UserRepository;
import com.gmail.askvorchevski.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User getUserById(Integer id, Connection connection) {
        String insertSQL = "SELECT * FROM user WHERE userId = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet);
                }
            } catch (SQLException ex) {
                logger.error("User didn't find by id: " + id, ex);
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            logger.error("Not fount", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    private User getUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setUserId(Integer.parseInt(resultSet.getString("userId")));
            user.setName(resultSet.getString("name"));
            user.setSureName(resultSet.getString("sureName"));
        } catch (SQLException e) {
            logger.error("User didn't exists", e);
            throw new RuntimeException(e);
        }
        return user;
    }
}
