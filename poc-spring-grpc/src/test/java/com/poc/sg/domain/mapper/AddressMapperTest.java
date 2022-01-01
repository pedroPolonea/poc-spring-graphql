package com.poc.sg.domain.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.poc.sg.domain.mapper.AddressMapper.addressDTOToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.addressMessageToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressDTO;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressMessage;
import static com.poc.sg.factory.AddressFactory.createAddress;
import static com.poc.sg.factory.AddressFactory.createAddressDTO;
import static com.poc.sg.factory.AddressFactory.createMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressMapperTest {

    @Test
    void shouldMapperDtoToEntity() {
        final var addressDTO = createAddressDTO();
        final var address = addressDTOToEntity(addressDTO);

        assertEquals(address.getId(), addressDTO.getId());
        assertEquals(address.getCity(), addressDTO.getCity());
        assertEquals(address.getNumber(), addressDTO.getNumber());
        assertEquals(address.getAddress(), addressDTO.getAddress());
        assertEquals(address.getDistrict(), addressDTO.getDistrict());
        assertEquals(address.getFederativeUnit(), addressDTO.getFederativeUnit());
    }

    @Test
    void shouldMapperEntityToDto() {
        final var address = createAddress();
        final var addressDTO = entityToAddressDTO(address);

        assertEquals(addressDTO.getId(), address.getId());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getNumber(), address.getNumber());
        assertEquals(addressDTO.getAddress(), address.getAddress());
        assertEquals(addressDTO.getDistrict(), address.getDistrict());
        assertEquals(addressDTO.getFederativeUnit(), address.getFederativeUnit());
    }

    @Test
    void shouldMapperMessageToEntity() {
        final var addressMessage = createMessage();
        final var address = addressMessageToEntity(addressMessage);

        assertEquals(addressMessage.getId(), address.getId());
        assertEquals(addressMessage.getCity(), address.getCity());
        assertEquals(addressMessage.getNumber(), address.getNumber());
        assertEquals(addressMessage.getAddress(), address.getAddress());
        assertEquals(addressMessage.getDistrict(), address.getDistrict());
        assertEquals(addressMessage.getFederativeUnit(), address.getFederativeUnit());
    }

    @Test
    void shouldMapperEntityToMessage() {
        final var address = createAddress();
        final var addressMessage = entityToAddressMessage(address);

        assertEquals(address.getId(), addressMessage.getId());
        assertEquals(address.getCity(), addressMessage.getCity());
        assertEquals(address.getNumber(), addressMessage.getNumber());
        assertEquals(address.getAddress(), addressMessage.getAddress());
        assertEquals(address.getDistrict(), addressMessage.getDistrict());
        assertEquals(address.getFederativeUnit(), addressMessage.getFederativeUnit());
    }
}
