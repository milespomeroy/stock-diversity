package com.milespomeroy.jdbi;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface UserDAO {
    @SqlQuery("SELECT name FROM users")
    List<String> findAll();

    void close();
}
