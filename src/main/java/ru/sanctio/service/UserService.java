package ru.sanctio.service;

import jakarta.ejb.Local;

import java.io.Serializable;

@Local
public interface UserService extends Serializable {
    boolean checkUser(String login, String password);

}
