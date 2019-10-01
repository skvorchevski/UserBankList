package com.gmail.askvorchevski.service.impl;

import com.gmail.askvorchevski.repository.DataBaseRepository;
import com.gmail.askvorchevski.repository.configuration.ConfigurationManager;
import com.gmail.askvorchevski.repository.connection.ConnectionService;
import com.gmail.askvorchevski.repository.connection.impl.ConnectionServiceImpl;
import com.gmail.askvorchevski.repository.impl.DataBaseRepositoryImpl;
import com.gmail.askvorchevski.service.DataBaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public class DataBaseServiceImpl implements DataBaseService {
    private static final Logger logger = LogManager.getLogger(DataBaseServiceImpl.class);
    private DataBaseRepository dataBaseRepository = DataBaseRepositoryImpl.getInstance();
    private static DataBaseServiceImpl instance;
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();


    private DataBaseServiceImpl() {
    }

    public static DataBaseServiceImpl getInstance() {
        if (instance == null) {
            return instance = new DataBaseServiceImpl();
        }
        return instance;
    }

    @Override
    public void createDataBase() {
        String myDataBase = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File resource = new File(Objects.requireNonNull(classLoader.getResource("DataBaseScript.sql")).getFile());
//        String resource = Objects.requireNonNull(getClass().getClassLoader().getResource("DataBaseScript.sql")).toString();
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.endsWith(";")) {
                        stringBuilder.append(line);
                        myDataBase = stringBuilder.toString();
                        dataBaseRepository.uploadDataBase(myDataBase, connection);
                        stringBuilder = new StringBuilder();
                    } else stringBuilder.append(line);
                }
                logger.info("Database has been created");
                connection.commit();
            } catch (IOException e) {
                connection.rollback();
                logger.error("Database failed", e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            logger.error("DataBase didn't upload ", e);
            throw new RuntimeException(e);
        }
    }
}
