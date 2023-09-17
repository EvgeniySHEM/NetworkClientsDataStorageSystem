package ru.sanctio.dao.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import ru.sanctio.dao.DBManagerDelete;
import ru.sanctio.dao.DBManagerSelect;
import ru.sanctio.dao.DataSource;
import ru.sanctio.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class DBManagerDeleteImpl implements DBManagerDelete {
    @EJB
    private DBManagerSelect dbManagerSelect;

    @Override
    public void deleteAddressById(String addressId) {
        Address address = dbManagerSelect.selectAddressById(addressId);
        int clientId = -1;

        if(address != null) {
            clientId = address.getClient().getClientId();
        }

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM address WHERE id = ?")) {

            preparedStatement.setInt(1, Integer.parseInt(addressId));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!checkAddressByClientId(clientId)) {
            deleteClientWithoutAddressById(clientId);
        }
    }

    private boolean checkAddressByClientId(int clientId) {
        if(clientId == -1) {
            return true;
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM address WHERE clientId = ?")) {

            preparedStatement.setInt(1, clientId);

            List<Address> addresses = null;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                addresses = dbManagerSelect.readAddressesFromResultSet(resultSet);
            }

            if (addresses.isEmpty()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void deleteClientWithoutAddressById(int clientId) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM client WHERE clientid = ?")) {

            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
