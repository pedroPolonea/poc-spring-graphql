package com.poc.sg.factory;

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
}
