package ru.sanctio.dao;

import jakarta.ejb.Local;

@Local
public interface DBManagerDelete {
    void deleteAddressById(String addressId);
}
