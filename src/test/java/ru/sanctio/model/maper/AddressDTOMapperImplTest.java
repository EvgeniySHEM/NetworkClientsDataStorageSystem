package ru.sanctio.model.maper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddressDTOMapperImplTest {

    private AddressDTOMapperImpl addressDTOMapper;

    @BeforeEach
    void setUp() {
        addressDTOMapper = new AddressDTOMapperImpl();
    }

    @Test
    void mapToEntity() {
        ClientDTO clientDTO = new ClientDTO(0, "Миша", "Юридическое лицо", "2023-09-07");
        AddressDTO addressDTO = new AddressDTO(0, "123.32.23.123", "er-re-er-ty-yu-rt", "model1",
                "address1", clientDTO);

        Address address = addressDTOMapper.mapToEntity(addressDTO);

        assertEquals(addressDTO.id(), address.getId());
        assertEquals(addressDTO.ip(), address.getIp());
        assertEquals(addressDTO.mac(), address.getMac());
        assertEquals(addressDTO.model(), address.getModel());
        assertEquals(addressDTO.address(), address.getAddress());
        assertEquals(addressDTO.client().clientId(), address.getClient().getClientId());
        assertEquals(addressDTO.client().clientName(), address.getClient().getClientName());
        assertEquals(addressDTO.client().type(), address.getClient().getType());
        assertEquals(addressDTO.client().added(), address.getClient().getAdded());
    }

    @Test
    void mapToEntity_ShouldReturnNull() {
        AddressDTO addressDTO = null;

        Address address = addressDTOMapper.mapToEntity(addressDTO);

        assertNull(address);
    }

    @Test
    void mapToDto() {
        Client client = new Client(0, "Миша", "Юридическое лицо", "2023-09-07");
        Address address = new Address(0, "123.32.23.123", "er-re-er-ty-yu-rt", "model1",
                "address1", client);

        AddressDTO addressDTO = addressDTOMapper.mapToDto(address);

        assertEquals(address.getId(), addressDTO.id());
        assertEquals(address.getIp(), addressDTO.ip());
        assertEquals(address.getMac(), addressDTO.mac());
        assertEquals(address.getModel(), addressDTO.model());
        assertEquals(address.getAddress(), addressDTO.address());
        assertEquals(address.getClient().getClientId(), addressDTO.client().clientId());
        assertEquals(address.getClient().getClientName(), addressDTO.client().clientName());
        assertEquals(address.getClient().getType(), addressDTO.client().type());
        assertEquals(address.getClient().getAdded(), addressDTO.client().added());
    }

    @Test
    void mapToDto_ShouldReturnNull() {
        Address address = null;

        AddressDTO addressDTO = addressDTOMapper.mapToDto(address);

        assertNull(addressDTO);
    }

    @Test
    void mapToDtoList() {
        Client client = new Client(0, "Миша", "Юридическое лицо", "2023-09-07");
        Address address = new Address(0, "123.32.23.123", "er-re-er-ty-yu-rt", "model1",
                "address1", client);
        List<Address> list = new ArrayList<>();
        list.add(address);

        List<AddressDTO> addressDTOList = addressDTOMapper.mapToDtoList(list);

        assertEquals(list.get(0).getId(), addressDTOList.get(0).id());
        assertEquals(list.get(0).getIp(), addressDTOList.get(0).ip());
        assertEquals(list.get(0).getMac(), addressDTOList.get(0).mac());
        assertEquals(list.get(0).getModel(), addressDTOList.get(0).model());
        assertEquals(list.get(0).getAddress(), addressDTOList.get(0).address());
        assertEquals(list.get(0).getClient().getClientId(), addressDTOList.get(0).client().clientId());
        assertEquals(list.get(0).getClient().getClientName(), addressDTOList.get(0).client().clientName());
        assertEquals(list.get(0).getClient().getType(), addressDTOList.get(0).client().type());
        assertEquals(list.get(0).getClient().getAdded(), addressDTOList.get(0).client().added());
    }

    @Test
    void mapToDtoList_ShouldReturnNull() {
        List<Address> list = null;

        List<AddressDTO> addressDTOList = addressDTOMapper.mapToDtoList(list);

        assertNull(addressDTOList);
    }
}