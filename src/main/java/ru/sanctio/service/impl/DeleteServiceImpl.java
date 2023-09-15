package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerDelete;
import ru.sanctio.service.DeleteService;

@Stateless
public class DeleteServiceImpl implements DeleteService {

    @EJB
    private DBManagerDelete dbManagerDelete;

    @Override
    public void deleteAddress(String addressId) {
        dbManagerDelete.deleteAddressById(addressId);
    }
}
