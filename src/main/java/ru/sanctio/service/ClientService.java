package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

@Local
public interface ClientService {
    boolean createNewClient(ClientDTO clientDTO, AddressDTO addressDTO);
    boolean updateClient(ClientDTO clientDTO, AddressDTO addressDTO);
}
