package com.gmail.askvorchevski.service.impl;

import com.gmail.askvorchevski.repository.UserRepository;
import com.gmail.askvorchevski.repository.connection.ConnectionService;
import com.gmail.askvorchevski.repository.connection.impl.ConnectionServiceImpl;
import com.gmail.askvorchevski.repository.impl.UserRepositoryImpl;
import com.gmail.askvorchevski.repository.model.User;
import com.gmail.askvorchevski.service.UserService;
import com.gmail.askvorchevski.service.converter.UserConverter;
import com.gmail.askvorchevski.service.converter.impl.UserConverterImpl;
import com.gmail.askvorchevski.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    private UserConverter userConverter = UserConverterImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public UserDTO getUserDTObyId(Integer id) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                User userById = userRepository.getUserById(id, connection);
                connection.commit();
                return userConverter.toDTO(userById);
            } catch (SQLException e) {
                connection.rollback();
                logger.error("User didn't find", e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            logger.error("Connection failed", e);
            throw new RuntimeException(e);
        }
    }
}
