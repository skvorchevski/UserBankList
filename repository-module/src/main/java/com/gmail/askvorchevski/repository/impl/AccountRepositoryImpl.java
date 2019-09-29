package com.gmail.askvorchevski.repository.impl;

import com.gmail.askvorchevski.repository.AccountRepository;
import com.gmail.askvorchevski.repository.model.Account;
import com.gmail.askvorchevski.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = LogManager.getLogger(AccountRepositoryImpl.class);
    private static AccountRepositoryImpl instance;

    private AccountRepositoryImpl() {
    }

    public static AccountRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AccountRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Account> findAllAccounts(Connection connection) {
        List<Account> accounts = new ArrayList<>();
        String insertSQL = "SELECT u.*, a.accountId, a.account FROM account a JOIN user u ON u.userId = a.userId;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Account account = getAccount(resultSet);
                    accounts.add(account);
                }
                return accounts;
            }
        } catch (SQLException e) {
            logger.error("Accounts didn't find", e);
            throw new RuntimeException(e);
        }
    }

    private Account getAccount(ResultSet resultSet) {
        Account account = new Account();
        User user = new User();
        try {
            user.setUserId(Integer.parseInt(resultSet.getString("userId")));
            user.setName(resultSet.getString("name"));
            user.setSureName(resultSet.getString("sureName"));
            account.setAccountId(Integer.parseInt(resultSet.getString("accountId")));
            account.setAccount(Integer.parseInt(resultSet.getString("account")));
            account.setUser(user);
        } catch (SQLException e) {
            logger.error("Account didn't find", e);
            throw new RuntimeException(e);
        }
        return account;
    }
}
