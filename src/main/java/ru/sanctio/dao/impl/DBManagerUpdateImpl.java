package ru.sanctio.dao.impl;

import jakarta.ejb.Singleton;
import ru.sanctio.dao.DBManagerUpdate;
import ru.sanctio.dao.DataSource;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
public class DBManagerUpdateImpl implements DBManagerUpdate {

    @Override
    public boolean update(Client newClient, Address newAddress) {
        if(updateClient(newClient) && updateAddress(newAddress)) {
            return true;
        }
        return false;
    }

    private boolean updateClient(Client newClient) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("update client " +
                     "set clientname = ?, type = ?, added = ? " +
                     "where clientid = ?")) {
            preparedStatement.setString(1, newClient.getClientName());
            preparedStatement.setString(2, newClient.getType());
            preparedStatement.setString(3, newClient.getAdded());
            preparedStatement.setInt(4, newClient.getClientId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean updateAddress(Address newAddress) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("update address " +
                     "set ip = ?, mac = ?, model = ?, address = ? " +
                     "where id = ?")) {
            preparedStatement.setString(1, newAddress.getIp());
            preparedStatement.setString(2, newAddress.getMac());
            preparedStatement.setString(3, newAddress.getModel());
            preparedStatement.setString(4, newAddress.getAddress());
            preparedStatement.setInt(5, newAddress.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
