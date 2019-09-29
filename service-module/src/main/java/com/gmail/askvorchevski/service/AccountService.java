package com.gmail.askvorchevski.service;

import com.gmail.askvorchevski.service.model.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAllAccounts();
}
