package com.poc.sg.factory;

import com.pb.proto.message.AddressMessage;
import com.poc.sg.domain.dto.AddressDTO;
import com.poc.sg.domain.entity.Address;

import static com.poc.sg.config.TestConfig.getFaker;

public class AddressFactory {

    public static AddressDTO createAddressDTO() {
        return AddressDTO.builder()
                .address(getFaker().country().name())
                .district(getFaker().nation().nationality())
                .city(getFaker().nation().capitalCity())
                .federativeUnit("BR")
                .id(getFaker().number().randomNumber())
                .number(getFaker().number().randomNumber())
                .build();
    }

    public static Address createAddress() {
        return new Address(
                getFaker().number().randomNumber(),
                getFaker().country().name(),
                getFaker().number().randomNumber(),
                getFaker().nation().nationality(),
                getFaker().nation().capitalCity(),
                "BR"
        );
    }

    public static AddressMessage createMessage() {
        return AddressMessage.newBuilder()
                .setAddress(getFaker().country().name())
                .setCity(getFaker().nation().capitalCity())
                .setDistrict(getFaker().nation().nationality())
                .setNumber(getFaker().number().randomNumber())
                .setFederativeUnit("BR")
                .build();
    }
}
