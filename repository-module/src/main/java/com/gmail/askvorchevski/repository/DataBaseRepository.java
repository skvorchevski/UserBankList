package com.gmail.askvorchevski.repository;

import java.sql.Connection;

public interface DataBaseRepository {
    void uploadDataBase(String myDataBase, Connection connection);
}