package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

@Local
public interface CreateService {
    boolean addClientAddress(Address newAddress, String clientId);

    boolean createNewClient(ClientDTO clientDTO, AddressDTO addressDTO);

}
