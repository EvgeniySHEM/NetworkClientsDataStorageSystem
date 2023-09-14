package ru.sanctio.model.maper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

import java.util.List;

@Mapper
public interface AddressDTOMapper {

    AddressDTOMapper INSTANCE = Mappers.getMapper( AddressDTOMapper.class );

    Address mapToEntity(AddressDTO addressDTO);

    AddressDTO mapToDto(Address address);

    List<AddressDTO> mapToDtoList(List<Address> addresses);
}
