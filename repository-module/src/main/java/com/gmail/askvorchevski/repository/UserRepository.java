package com.gmail.askvorchevski.repository;

import com.gmail.askvorchevski.repository.module.User;

import java.sql.Connection;

public interface UserRepository {
    User getUserById(Integer id, Connection connection);
}
