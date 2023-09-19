package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;
import ru.sanctio.model.dto.AddressDTO;

import java.util.List;

@Local
public interface AddressService {
    List<AddressDTO> getSortedData();

    AddressDTO selectAddressById(String addressId);

    void deleteAddress(String addressId);
}
