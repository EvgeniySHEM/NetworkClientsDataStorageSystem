package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerSelect;
import ru.sanctio.dao.DBManagerUpdate;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.model.maper.AddressDTOMapper;
import ru.sanctio.model.maper.ClientDTOMapper;
import ru.sanctio.service.UpdateService;

@Stateless
public class UpdateServiceImpl implements UpdateService {
    @EJB
    private DBManagerSelect dbManagerSelect;

    @EJB
    private DBManagerUpdate dbManagerUpdate;

    @Override
    public AddressDTO selectAddress(String addressId) {
        Address address = dbManagerSelect.selectAddressById(addressId);
        AddressDTO addressDTO = AddressDTOMapper.INSTANCE.mapToDto(address);
        return addressDTO;
    }

    @Override
    public boolean updateClient(ClientDTO clientDTO, AddressDTO addressDTO) {
        Client newClient = ClientDTOMapper.INSTANCE.mapToEntity(clientDTO);
        Address newAddress = AddressDTOMapper.INSTANCE.mapToEntity(addressDTO);
        newClient.addAddress(newAddress);
        return dbManagerUpdate.update(newClient, newAddress);
    }
}
