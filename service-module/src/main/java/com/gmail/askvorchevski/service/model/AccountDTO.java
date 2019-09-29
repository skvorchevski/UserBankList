package com.gmail.askvorchevski.service.model;

public class AccountDTO {
    private Integer accountId;
    private Integer account;
    private UserDTO userDTO;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", account=" + account +
                ", userDTO=" + userDTO +
                '}';
    }
}
