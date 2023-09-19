package ru.sanctio.dao;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;

import java.util.List;

@Local
public interface DBManagerAddress {
    List<Address> getAllInformation();

    Address selectAddressById(String addressId);

    void deleteAddressById(String addressId);
}
