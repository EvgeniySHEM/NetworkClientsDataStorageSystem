package ru.sanctio.dao;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Local
public interface DBManagerSelect {
    List<Address> getAllInformation();

    Address selectAddressById(String addressId);

    List<Address> readAddressesFromResultSet(ResultSet resultSet) throws SQLException;
}
