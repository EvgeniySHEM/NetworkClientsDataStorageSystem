package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.dto.AddressDTO;

@Local
public interface UpdateService {
    AddressDTO selectAddress(String addressId);
}
