package ru.sanctio.dao.impl;

import jakarta.ejb.Singleton;
import ru.sanctio.dao.DBManagerCreate;
import ru.sanctio.dao.DataSource;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class DBManagerCreateImpl implements DBManagerCreate {

    @Override
    public boolean createNewClient(Client newClient, Address newAddress) {
        newClient = createClientEntity(newClient);
        newAddress.setClient(newClient);
        return createAddressEntity(newAddress);
    }

    private Client createClientEntity(Client newClient) {
        Optional<Client> client = findClientNotById(newClient);
        if (client.isPresent()) {
            return client.get();
        } else {
            try (Connection con = DataSource.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement("insert into " +
                         "client (clientname, type, added) VALUES (?,?,?)")) {
                preparedStatement.setString(1, newClient.getClientName());
                preparedStatement.setString(2, newClient.getType());
                preparedStatement.setString(3, newClient.getAdded());

                preparedStatement.executeUpdate();

                return findClientNotById(newClient).get();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Optional<Client> findClientNotById(Client client) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select c.clientid, c.clientname, " +
                     "c.type, c.added from client as c " +
                     "where clientName = ? and type = ? and added = ?")) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getType());
            preparedStatement.setObject(3, client.getAdded());

            List<Client> clients = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    clients.add(new Client(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getDate(4).toString()));
                    return Optional.of(clients.get(0));
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean createAddressEntity(Address newAddress) {
        Optional<Address> address = findAddressNotById(newAddress);
        if (address.isPresent()) {
            return false;
        } else {
            try (Connection con = DataSource.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement("insert into " +
                         "address (ip, mac, model, address, clientid) VALUES (?,?,?,?,?)")) {
                preparedStatement.setString(1, newAddress.getIp());
                preparedStatement.setString(2, newAddress.getMac());
                preparedStatement.setString(3, newAddress.getModel());
                preparedStatement.setString(4, newAddress.getAddress());
                preparedStatement.setInt(5, newAddress.getClient().getClientId());

                preparedStatement.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Optional<Address> findAddressNotById(Address newAddress) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select a.id, a.ip, a.mac, " +
                     "a.model, a.address, a.clientid  from address as a " +
                     "where ip = ? and mac = ? and model = ? and address = ?")) {
            preparedStatement.setString(1, newAddress.getIp());
            preparedStatement.setString(2, newAddress.getMac());
            preparedStatement.setString(3, newAddress.getModel());
            preparedStatement.setString(4, newAddress.getAddress());

            List<Address> addresses = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    addresses.add(new Address(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4),
                            resultSet.getString(5),
                            new Client(resultSet.getInt(6), resultSet.getString(7),
                                    resultSet.getString(8),
                                    resultSet.getDate(9).toLocalDate().toString())));
                    return Optional.of(addresses.get(0));
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
