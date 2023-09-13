package ru.sanctio.service;

import jakarta.ejb.Local;
import ru.sanctio.model.Address;

import java.util.List;

@Local
public interface SelectService {

    List<Address> getData();
    List<Address> getSortedData();
}
