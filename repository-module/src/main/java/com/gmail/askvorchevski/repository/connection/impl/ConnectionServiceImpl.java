package com.gmail.askvorchevski.repository.connection.impl;

import com.gmail.askvorchevski.repository.configuration.ConfigurationManager;
import com.gmail.askvorchevski.repository.connection.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.gmail.askvorchevski.repository.configuration.ConfigurationManager.DATABASE_PWD;
import static com.gmail.askvorchevski.repository.configuration.ConfigurationManager.DATABASE_URL;
import static com.gmail.askvorchevski.repository.configuration.ConfigurationManager.DATABASE_USERNAME;

public class ConnectionServiceImpl implements ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionServiceImpl.class);
    private static ConnectionServiceImpl instance;
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    private ConnectionServiceImpl() {
        logger.info("------------------------MySQL JDBC CONNECTION TESTING--------------------------");
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
            logger.info("---------------------MySQL JDBC DRIVER REGISTERED--------------------------");
        } catch (ClassNotFoundException e) {
            logger.error("DataBase driver does'n found", e);
            throw new RuntimeException(e);
        }
    }

    public static ConnectionServiceImpl getInstance() {
        if (instance == null) {
            return instance = new ConnectionServiceImpl();
        } else return instance;
    }

    @Override
    public Connection getConnection() {
        logger.info("----------------------------CREATING CONNECTION...--------------------------");
        Properties properties = new Properties();
        properties.setProperty("user", configurationManager.getProperty(DATABASE_USERNAME));
        properties.setProperty("password", configurationManager.getProperty(DATABASE_PWD));
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "cp1251");
        Connection connection;
        try {
            connection = DriverManager.getConnection(configurationManager.getProperty(DATABASE_URL), properties);
            logger.info("-----------------------CONNECTION WAS CREATED----------------------------");
        } catch (SQLException e) {
            logger.error("-------------------------CONNECTION FAILED!---------------------------------", e);
            throw new RuntimeException(e);
        }
        return connection;
    }
}
