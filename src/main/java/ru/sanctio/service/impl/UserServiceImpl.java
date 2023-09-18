package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerUser;
import ru.sanctio.service.UserService;

import java.io.Serializable;

@Stateless
public class UserServiceImpl implements UserService, Serializable {

    @EJB
    private DBManagerUser dbManagerUser;

    public boolean checkUser(String login, String password) {
        return dbManagerUser.checkUser(login, password);
    }
}
