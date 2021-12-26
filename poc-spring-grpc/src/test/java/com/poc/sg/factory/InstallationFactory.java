package com.poc.sg.factory;

import com.poc.sg.domain.dto.InstallationDTO;
import com.poc.sg.domain.entity.Installation;

import java.time.LocalDateTime;

import static com.poc.sg.config.TestConfig.getFaker;
import static com.poc.sg.factory.AddressFactory.createAddress;
import static com.poc.sg.factory.AddressFactory.createAddressDTO;

public class InstallationFactory {

    public static InstallationDTO createInstallationDTO(){
        return InstallationDTO.builder()
                .dataInstallation(LocalDateTime.now().toString())
                .code(getFaker().numerify("123###09"))
                .id(getFaker().number().randomNumber())
                .client(ClientFactory.createClientDTO())
                .addressInstallation(createAddressDTO())
                .build();
    }

    public static Installation createInstallation(){
        return new Installation(
                getFaker().number().randomNumber(),
                getFaker().numerify("123###09"),
                LocalDateTime.now(),
                ClientFactory.createClient(),
                createAddress(),
                null
        );
    }
}
