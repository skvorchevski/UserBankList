package com.gmail.askvorchevski.service.converter;

import com.gmail.askvorchevski.repository.model.Account;
import com.gmail.askvorchevski.service.model.AccountDTO;

public interface AccountConverter {
    AccountDTO toDTO(Account account);

    Account fromDTO(AccountDTO accountDTO);
}
