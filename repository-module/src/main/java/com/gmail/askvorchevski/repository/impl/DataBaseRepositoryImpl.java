package com.gmail.askvorchevski.repository.impl;

import com.gmail.askvorchevski.repository.DataBaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseRepositoryImpl implements DataBaseRepository {
    private static final Logger logger = LogManager.getLogger(DataBaseRepositoryImpl.class);
    private static DataBaseRepositoryImpl instance;

    private DataBaseRepositoryImpl() {
    }

    public static DataBaseRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DataBaseRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void uploadDataBase(String myDataBase, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(myDataBase);
            logger.info("----------------------------DataBase upload successful----------------------------");
        } catch (SQLException e) {
            logger.error("DataBase didn't upload ", e);
            throw new RuntimeException(e);
        }
    }
}
