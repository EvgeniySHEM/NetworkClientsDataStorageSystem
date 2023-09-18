package ru.sanctio.model.maper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.ClientDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientDTOMapperImplTest {

    private ClientDTOMapper clientDTOMapper;

    @BeforeEach
    void setUp() {
        clientDTOMapper = new ClientDTOMapperImpl();
    }

    @Test
    void mapToEntity() {
        ClientDTO clientDTO = new ClientDTO(0, "Миша", "Юридическое лицо", "2023-09-07");

        Client client = clientDTOMapper.mapToEntity(clientDTO);

        assertEquals(clientDTO.clientId(), client.getClientId());
        assertEquals(clientDTO.clientName(), client.getClientName());
        assertEquals(clientDTO.type(), client.getType());
        assertEquals(clientDTO.added(), client.getAdded());
    }

    @Test
    void mapToEntity_ShouldReturnNull() {
        ClientDTO clientDTO = null;

        Client client = clientDTOMapper.mapToEntity(clientDTO);

        assertNull(client);
    }

    @Test
    void mapToDto() {
        Client client = new Client(0, "Миша", "Юридическое лицо", "2023-09-07");

        ClientDTO clientDTO = clientDTOMapper.mapToDto(client);

        assertEquals(client.getClientId(), clientDTO.clientId());
        assertEquals(client.getClientName(), clientDTO.clientName());
        assertEquals(client.getType(), clientDTO.type());
        assertEquals(client.getAdded(), clientDTO.added());
    }

    @Test
    void mapToDto_ShouldReturnNull() {
        Client client = null;

        ClientDTO clientDTO = clientDTOMapper.mapToDto(client);

        assertNull(clientDTO);
    }
}