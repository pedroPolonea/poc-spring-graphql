package com.poc.sg.domain.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.poc.sg.domain.mapper.AddressMapper.addressDTOToEntity;
import static com.poc.sg.factory.AddressFactory.createAddress;
import static com.poc.sg.factory.AddressFactory.createAddressDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressMapperTest {

    @Test
    void shouldMapperDtoToEntity(){
        final var addressDTO = createAddressDTO();
        final var address = addressDTOToEntity(addressDTO);

        assertEquals(address.getAddress(), addressDTO.getAddress());
        assertEquals(address.getCity(), addressDTO.getCity());
        assertEquals(address.getDistrict(), addressDTO.getDistrict());
        assertEquals(address.getFederativeUnit(), addressDTO.getFederativeUnit());
        assertEquals(address.getNumber(), addressDTO.getNumber());
        assertEquals(address.getId(), addressDTO.getId());
    }

    @Test
    void shouldMapperEntityToDto(){
        final var address = createAddress();
        final var addressDTO = AddressMapper.entityToAddressDTO(address);

        assertEquals(addressDTO.getAddress(), address.getAddress());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getDistrict(), address.getDistrict());
        assertEquals(addressDTO.getFederativeUnit(), address.getFederativeUnit());
        assertEquals(addressDTO.getNumber(), address.getNumber());
        assertEquals(addressDTO.getId(), address.getId());
    }



}