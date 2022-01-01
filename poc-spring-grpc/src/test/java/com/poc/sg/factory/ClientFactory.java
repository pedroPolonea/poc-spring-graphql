package com.poc.sg.factory;

import com.pb.proto.message.AddressMessage;
import com.pb.proto.message.ClientMessage;
import com.poc.sg.domain.dto.ClientDTO;
import com.poc.sg.domain.entity.Client;

import java.time.LocalDateTime;

import static com.poc.sg.config.TestConfig.getFaker;
import static com.poc.sg.factory.AddressFactory.createAddress;
import static com.poc.sg.factory.AddressFactory.createAddressDTO;

public class ClientFactory {

    public static ClientDTO createClientDTO() {
        return ClientDTO.builder()
                .birthDate(LocalDateTime.now().toString())
                .id(getFaker().number().randomNumber())
                .name(getFaker().name().name())
                .document(getFaker().numerify("074########"))
                .addressCharge(createAddressDTO())
                .build();
    }

    public static Client createClient() {
        return new Client(
                getFaker().number().randomNumber(),
                getFaker().name().name(),
                getFaker().numerify("074########"),
                LocalDateTime.now(),
                createAddress(),
                null
        );
    }

    public static ClientMessage createMessage(final AddressMessage addressMessage) {
        return ClientMessage.newBuilder()
                .setName(getFaker().name().name())
                .setBirthDate(LocalDateTime.now().toString())
                .setDocument(getFaker().numerify("074########"))
                .setAddressCharge(addressMessage)
                .build();
    }
}
