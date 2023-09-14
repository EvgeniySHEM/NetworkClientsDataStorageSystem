package ru.sanctio.dao.impl;

import jakarta.ejb.Singleton;
import ru.sanctio.dao.DBManagerSelect;
import ru.sanctio.dao.DataSource;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBManagerSelectImpl implements DBManagerSelect {
    @Override
    public List<Address> getAllInformation() {
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT a.id,a.ip,a.mac, a.model, a.address, c.clientid, c.clientname, c.type, c.added " +
                     "FROM address as a join client c on c.clientid = a.clientid")) {

            return readAddressesFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Address> readAddressesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Address> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(new Address(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5),
                    new Client(resultSet.getInt(6), resultSet.getString(7),
                            resultSet.getString(8), resultSet.getString(9))));
        }
        return res;
    }

    @Override
    public Address selectAddressById(String addressId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT a.id,a.ip,a.mac, " +
                     "a.model, a.address,c.clientid, c.clientname, c.type, c.added " +
                     "FROM address as a join client c on c.clientid = a.clientid where a.id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(addressId));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return readAddressFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Address readAddressFromResultSet(ResultSet resultSet) {
        Address res = null;
        try {
            if(resultSet.next()) {
                res = new Address(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5),
                        new Client(resultSet.getInt(6), resultSet.getString(7),
                                resultSet.getString(8), resultSet.getString(9)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
