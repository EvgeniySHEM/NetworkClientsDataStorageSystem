package ru.sanctio.dao;

import jakarta.ejb.Local;

@Local
public interface DBManagerUser {
    boolean checkUser(String login, String password);
}
