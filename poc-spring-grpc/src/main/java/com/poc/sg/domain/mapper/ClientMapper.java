package com.poc.sg.domain.mapper;

import com.poc.sg.domain.dto.ClientDTO;
import com.poc.sg.domain.entity.Client;

import static com.poc.sg.domain.mapper.AddressMapper.addressDTOToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressDTO;
import static java.time.LocalDateTime.parse;

public class ClientMapper {

    public static Client clientDTOToEntity(final ClientDTO clientDTO) {
        return new Client(
                clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getDocument(),
                parse(clientDTO.getBirthDate()),
                addressDTOToEntity(clientDTO.getAddressCharge()),
                null
        );
    }

    public static ClientDTO entityToClientDTO(final Client client) {
        return ClientDTO.builder()
                .birthDate(client.getBirthDate().toString())
                .document(client.getDocument())
                .addressCharge(entityToAddressDTO(client.getAddressCharge()))
                .name(client.getName())
                .id(client.getId())
                .build();
    }
}
