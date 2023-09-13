package ru.sanctio.dao;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;

import java.util.List;

@Local
public interface DBManagerSelect {
    List<Address> getAllInformation();
}
