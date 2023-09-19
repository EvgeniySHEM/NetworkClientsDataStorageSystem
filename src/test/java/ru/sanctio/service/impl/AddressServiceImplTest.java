package ru.sanctio.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerAddress;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private Client client;
    private Address address;
    private Client client2;
    private Address address2;

    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private DBManagerAddress dbManagerAddress;

    @BeforeEach
    void create() {
        client = new Client(1, "Макс", "Физическое лицо", "2022-02-01");
        address = new Address(1, "111.111.111.111", "dd-dd-dd-dd-dd-dd",
                "model", "address", client);

        client2 = new Client(2, "Паша", "Физическое лицо", "2022-10-13");
        address2 = new Address(2, "222.222.222.222", "dd-tt-dd-tt-dd-dd",
                "model10", "address10", client2);
    }

    @Test
    void getSortedData() {
        List<Address> list = new ArrayList<>();
        list.add(address2);
        list.add(address);
        when(dbManagerAddress.getAllInformation()).thenReturn(list);

        List<AddressDTO> sortedData = addressService.getSortedData();

        verify(dbManagerAddress, times(1)).getAllInformation();
        assertNotNull(sortedData);
        assertEquals(address.getClient().getClientId(), sortedData.get(0).client().clientId());
    }

    @Test
    void selectAddressById() {
        when(dbManagerAddress.selectAddressById(String.valueOf(address.getId()))).thenReturn(address);

        AddressDTO addressDTO = addressService.selectAddressById(String.valueOf(address.getId()));

        verify(dbManagerAddress, times(1))
                .selectAddressById(String.valueOf(address.getId()));
        assertNotNull(addressDTO);
    }

    @Test
    void deleteAddress() {
        addressService.deleteAddress(String.valueOf(address.getId()));

        verify(dbManagerAddress, times(1))
                .deleteAddressById(String.valueOf(address.getId()));
    }
}