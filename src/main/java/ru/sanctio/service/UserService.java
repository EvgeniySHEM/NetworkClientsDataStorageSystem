package ru.sanctio.service;

import jakarta.ejb.Local;

@Local
public interface UserService {
    boolean checkUser(String login, String password);

}
