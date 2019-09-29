package com.gmail.askvorchevski.service.converter.impl;

import com.gmail.askvorchevski.repository.model.Account;
import com.gmail.askvorchevski.service.converter.AccountConverter;
import com.gmail.askvorchevski.service.converter.UserConverter;
import com.gmail.askvorchevski.service.model.AccountDTO;

public class AccountConverterImpl implements AccountConverter {
    private UserConverter userConverter = UserConverterImpl.getInstance();
    private static AccountConverterImpl instance;

    private AccountConverterImpl() {
    }

    public static AccountConverterImpl getInstance() {
        if (instance == null) {
            instance = new AccountConverterImpl();
        }
        return instance;
    }

    @Override
    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAccount(account.getAccount());
        accountDTO.setUserDTO(userConverter.toDTO(account.getUser()));
        return accountDTO;
    }

    @Override
    public Account fromDTO(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        account.setAccount(accountDTO.getAccount());
        account.setUser(userConverter.fromDTO(accountDTO.getUserDTO()));
        return account;
    }
}
