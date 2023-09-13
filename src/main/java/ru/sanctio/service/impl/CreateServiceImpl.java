package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerCreate;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.model.maper.AddressDTOMapper;
import ru.sanctio.model.maper.ClientDTOMapper;
import ru.sanctio.service.CreateService;

@Stateless
public class CreateServiceImpl implements CreateService {

    @EJB
    private DBManagerCreate dbManagerCreate;

//    private AddressDTOMapper addressDTOMapper;
//    private ClientDTOMapper clientDTOMapper;
    
    @Override
    public boolean addClientAddress(Address newAddress, String clientId) {
        return false;
    }

    @Override
    public boolean createNewClient(ClientDTO clientDTO, AddressDTO addressDTO) {
        Client newClient = ClientDTOMapper.INSTANCE.mapToEntity(clientDTO);
        Address newAddress = AddressDTOMapper.INSTANCE.mapToEntity(addressDTO);
        newClient.addAddress(newAddress);
        dbManagerCreate.createNewClient(newClient, newAddress);




        return false;
    }
}
