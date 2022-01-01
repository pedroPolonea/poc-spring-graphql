package com.poc.sg.domain.mapper;

import com.pb.proto.message.AddressMessage;
import com.poc.sg.domain.dto.AddressDTO;
import com.poc.sg.domain.entity.Address;

public class AddressMapper {

    public static Address addressDTOToEntity(final AddressDTO addressDTO) {
        return new Address(
                addressDTO.getId(),
                addressDTO.getAddress(),
                addressDTO.getNumber(),
                addressDTO.getDistrict(),
                addressDTO.getCity(),
                addressDTO.getFederativeUnit()
        );
    }

    public static AddressDTO entityToAddressDTO(final Address address) {
        return AddressDTO.builder()
                .city(address.getCity())
                .district(address.getDistrict())
                .federativeUnit(address.getFederativeUnit())
                .id(address.getId())
                .address(address.getAddress())
                .number(address.getNumber())
                .build();
    }

    public static Address addressMessageToEntity(final AddressMessage addressMessage) {
        return new Address(
                addressMessage.getId(),
                addressMessage.getAddress(),
                addressMessage.getNumber(),
                addressMessage.getDistrict(),
                addressMessage.getCity(),
                addressMessage.getFederativeUnit()
        );
    }

    public static AddressMessage entityToAddressMessage(final Address address) {
        return AddressMessage.newBuilder()
                .setAddress(address.getAddress())
                .setCity(address.getCity())
                .setDistrict(address.getDistrict())
                .setFederativeUnit(address.getFederativeUnit())
                .setId(address.getId())
                .setNumber(address.getNumber())
                .build();
    }
}
