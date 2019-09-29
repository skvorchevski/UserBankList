package com.gmail.askvorchevski.service.impl;

import com.gmail.askvorchevski.repository.AccountRepository;
import com.gmail.askvorchevski.repository.connection.ConnectionService;
import com.gmail.askvorchevski.repository.connection.impl.ConnectionServiceImpl;
import com.gmail.askvorchevski.repository.impl.AccountRepositoryImpl;
import com.gmail.askvorchevski.repository.model.Account;
import com.gmail.askvorchevski.service.AccountService;
import com.gmail.askvorchevski.service.converter.AccountConverter;
import com.gmail.askvorchevski.service.converter.impl.AccountConverterImpl;
import com.gmail.askvorchevski.service.model.AccountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    private static AccountServiceImpl instance;
    private AccountConverter accountConverter = AccountConverterImpl.getInstance();
    private AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    @Override
    public List<AccountDTO> findAllAccounts() {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Account> accounts = accountRepository.findAllAccounts(connection);
                connection.commit();
                return getAccountDTO(accounts);
            } catch (SQLException ex) {
                connection.rollback();
                logger.error("Accounts didn't find", ex);
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            logger.error("Connection failed", e);
            throw new RuntimeException(e);
        }
    }

    private List<AccountDTO> getAccountDTO(List<Account> accounts) {
        return accounts.stream()
                .map(accountConverter::toDTO)
                .collect(Collectors.toList());
    }
}
