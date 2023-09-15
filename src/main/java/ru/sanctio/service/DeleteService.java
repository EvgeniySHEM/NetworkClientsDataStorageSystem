package ru.sanctio.service;

import jakarta.ejb.Local;

@Local
public interface DeleteService {
    void deleteAddress(String addressId);
}
