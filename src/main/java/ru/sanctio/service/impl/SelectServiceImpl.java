package ru.sanctio.service.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.sanctio.dao.DBManagerSelect;
import ru.sanctio.model.Address;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.maper.AddressDTOMapper;
import ru.sanctio.service.SelectService;

import java.util.List;

@Stateless
public class SelectServiceImpl implements SelectService {

    @EJB
    private DBManagerSelect dbManagerSelect;

    @Override
    public List<Address> getData() {
            return dbManagerSelect.getAllInformation();
    }

    @Override
    public List<AddressDTO> getSortedData() {
        List<Address> list = getData();
        list.sort((a, b) -> a.getClient().getClientId() - b.getClient().getClientId());
        List<AddressDTO> dtoList = AddressDTOMapper.INSTANCE.mapToDtoList(list);
        return dtoList;
    }
}
