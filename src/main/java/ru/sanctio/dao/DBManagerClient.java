package ru.sanctio.dao;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

@Local
public interface DBManagerClient {

    boolean createNewClient(Client newClient, Address newAddress);
    boolean update(Client newClient, Address newAddress);
}
