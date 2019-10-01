package com.gmail.askvorchevski.service.converter.impl;

import com.gmail.askvorchevski.repository.model.Account;
import com.gmail.askvorchevski.repository.model.User;
import com.gmail.askvorchevski.service.converter.AccountConverter;
import com.gmail.askvorchevski.service.model.AccountDTO;
import com.gmail.askvorchevski.service.model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountConverterImplTest {
    private AccountConverter accountConverter;

    @Before
    public void setUp() throws Exception {
        accountConverter = AccountConverterImpl.getInstance();
    }

    @Test
    public void ShouldReturnAccountID() {
        AccountDTO accountDTO = new AccountDTO();
        UserDTO userDTO = new UserDTO();
        accountDTO.setUserDTO(userDTO);
        accountDTO.setAccountId(1);
        Account account = accountConverter.fromDTO(accountDTO);
        assertEquals(accountDTO.getAccountId(), account.getAccountId());
    }

    @Test
    public void ShouldReturnAccount() {
        AccountDTO accountDTO = new AccountDTO();
        UserDTO userDTO = new UserDTO();
        accountDTO.setUserDTO(userDTO);
        accountDTO.setAccount(1000);
        Account account = accountConverter.fromDTO(accountDTO);
        assertEquals(accountDTO.getAccount(), account.getAccount());
    }

    @Test
    public void ShouldReturnUserID() {
        AccountDTO accountDTO = new AccountDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        accountDTO.setUserDTO(userDTO);
        Account account = accountConverter.fromDTO(accountDTO);
        assertEquals(accountDTO.getUserDTO().getUserId(), account.getUser().getUserId());
    }

    @Test
    public void ShouldReturnUserName() {
        AccountDTO accountDTO = new AccountDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Pavel");
        accountDTO.setUserDTO(userDTO);
        Account account = accountConverter.fromDTO(accountDTO);
        assertEquals(accountDTO.getUserDTO().getName(), account.getUser().getName());
    }

    @Test
    public void ShouldReturnUserSureName() {
        AccountDTO accountDTO = new AccountDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setSureName("Ivanov");
        accountDTO.setUserDTO(userDTO);
        Account account = accountConverter.fromDTO(accountDTO);
        assertEquals(accountDTO.getUserDTO().getSureName(), account.getUser().getSureName());
    }

    @Test
    public void ShouldReturnAccountDTOiD() {
        Account account = new Account();
        User user = new User();
        account.setUser(user);
        account.setAccountId(1);
        AccountDTO accountDTO = accountConverter.toDTO(account);
        assertEquals(account.getAccountId(), accountDTO.getAccountId());
    }

    @Test
    public void ShouldReturnAccountDTO() {
        Account account = new Account();
        User user = new User();
        account.setUser(user);
        account.setAccount(1000);
        AccountDTO accountDTO = accountConverter.toDTO(account);
        assertEquals(account.getAccount(), accountDTO.getAccount());
    }

    @Test
    public void ShouldReturnUserDTOiD() {
        Account account = new Account();
        User user = new User();
        user.setUserId(1);
        account.setUser(user);
        AccountDTO accountDTO = accountConverter.toDTO(account);
        assertEquals(account.getUser().getUserId(), accountDTO.getUserDTO().getUserId());
    }

    @Test
    public void ShouldReturnUserNameDTOs() {
        Account account = new Account();
        User user = new User();
        user.setName("Pavel");
        account.setUser(user);
        AccountDTO accountDTO = accountConverter.toDTO(account);
        assertEquals(account.getUser().getName(), accountDTO.getUserDTO().getName());
    }

    @Test
    public void ShouldReturnUserSureNameDTOs() {
        Account account = new Account();
        User user = new User();
        user.setSureName("Ivanov");
        account.setUser(user);
        AccountDTO accountDTO = accountConverter.toDTO(account);
        assertEquals(account.getUser().getSureName(), accountDTO.getUserDTO().getSureName());
    }
}