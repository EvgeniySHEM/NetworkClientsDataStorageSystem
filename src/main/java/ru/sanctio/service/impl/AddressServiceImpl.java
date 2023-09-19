package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerAddress;
import ru.sanctio.model.Address;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.maper.AddressDTOMapper;
import ru.sanctio.service.AddressService;

import java.util.List;

@Stateless
public class AddressServiceImpl implements AddressService {

    @EJB
    private DBManagerAddress dbManagerAddress;

    @Override
    public List<AddressDTO> getSortedData() {
        List<Address> list = dbManagerAddress.getAllInformation();
        list.sort((a, b) -> a.getClient().getClientId() - b.getClient().getClientId());
        return AddressDTOMapper.INSTANCE.mapToDtoList(list);
    }

    @Override
    public AddressDTO selectAddressById(String addressId) {
        Address address = dbManagerAddress.selectAddressById(addressId);
        return AddressDTOMapper.INSTANCE.mapToDto(address);
    }

    @Override
    public void deleteAddress(String addressId) {

        dbManagerAddress.deleteAddressById(addressId);
    }
}
