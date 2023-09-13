package ru.sanctio.dao;

import jakarta.ejb.Singleton;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBManagerCreateImpl implements DBManagerCreate{
    @Override
    public boolean createNewClient(Client newClient, Address newAddress) {
        newClient = createClientEntity(newClient);
        newAddress.setClient(newClient);
        return createAddressEntity(newAddress);
    }

    private Client createClientEntity(Client newClient) {
        if(findClientNotById(newClient) != null) {

        }

    }

    private Client findClientNotById(Client client) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select c.clientid, c.clientname, " +
                     "c.type, c.added from client as c " +
                     "where clientName = ? and type = ? and added = ?")) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getType());
            preparedStatement.setString(3, client.getAdded());

            List<Client> clients = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    clients.add(new Client(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getDate(4).toString()));
                    return clients.get(0);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
