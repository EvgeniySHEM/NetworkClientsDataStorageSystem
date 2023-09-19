package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerClient;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.model.maper.AddressDTOMapper;
import ru.sanctio.model.maper.ClientDTOMapper;
import ru.sanctio.service.ClientService;

@Stateless
public class ClientServiceImpl implements ClientService {

    @EJB
    private DBManagerClient dbManagerClient;

    @Override
    public boolean createNewClient(ClientDTO clientDTO, AddressDTO addressDTO) {
        Client newClient = ClientDTOMapper.INSTANCE.mapToEntity(clientDTO);
        Address newAddress = AddressDTOMapper.INSTANCE.mapToEntity(addressDTO);

        return dbManagerClient.createNewClient(newClient, newAddress);
    }

    @Override
    public boolean updateClient(ClientDTO clientDTO, AddressDTO addressDTO) {
        Client newClient = ClientDTOMapper.INSTANCE.mapToEntity(clientDTO);
        Address newAddress = AddressDTOMapper.INSTANCE.mapToEntity(addressDTO);
        return dbManagerClient.update(newClient, newAddress);
    }
}
