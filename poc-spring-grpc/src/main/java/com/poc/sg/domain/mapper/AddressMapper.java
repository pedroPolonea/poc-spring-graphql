package com.poc.sg.domain.mapper;

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
}
