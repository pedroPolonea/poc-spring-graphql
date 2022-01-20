package com.poc.sg.domain.mapper;

import com.pb.proto.message.ClientMessage;
import com.pb.proto.message.ClientMessageList;
import com.poc.sg.domain.dto.ClientDTO;
import com.poc.sg.domain.entity.Client;

import java.util.List;
import java.util.stream.Collectors;

import static com.poc.sg.domain.mapper.AddressMapper.addressDTOToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.addressMessageToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressDTO;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressMessage;
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

    public static Client clientMessageToEntity(final ClientMessage clientMessage) {
        return new Client(
                clientMessage.getId(),
                clientMessage.getName(),
                clientMessage.getDocument(),
                parse(clientMessage.getBirthDate()),
                addressMessageToEntity(clientMessage.getAddressCharge()),
                null
        );
    }

    public static ClientMessage entityToClientMessage(final Client client) {
        return ClientMessage.newBuilder()
                .setName(client.getName())
                .setBirthDate(client.getBirthDate().toString())
                .setDocument(client.getDocument())
                .setAddressCharge(entityToAddressMessage(client.getAddressCharge()))
                .setId(client.getId())
                .build();
    }

    public static ClientMessageList entityListToClientMessageList(final List<Client> clients) {


        return ClientMessageList.newBuilder()
                .addAllClientMessage(
                        clients.stream()
                            .map(ClientMapper::entityToClientMessage)
                            .collect(Collectors.toList()))
                .build();
    }
}
