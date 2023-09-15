package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

@Local
public interface UpdateService {
    AddressDTO selectAddress(String addressId);

    boolean updateClient(ClientDTO clientDTO, AddressDTO addressDTO);
}
