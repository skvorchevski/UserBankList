package com.gmail.askvorchevski.repository;

import com.gmail.askvorchevski.repository.module.Account;

import java.sql.Connection;
import java.util.List;

public interface AccountRepository {
    List<Account> findAllAccounts(Connection connection);
}
