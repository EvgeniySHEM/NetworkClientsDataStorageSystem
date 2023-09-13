package ru.sanctio.model.maper;

import jakarta.ejb.Local;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.ClientDTO;

@Mapper
public interface ClientDTOMapper {

    ClientDTOMapper INSTANCE = Mappers.getMapper( ClientDTOMapper.class );

    Client mapToEntity (ClientDTO clientDTO);
    ClientDTO mapToDto (Client client);
}
