package com.poc.sg.domain.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.poc.sg.domain.mapper.ClientMapper.clientDTOToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.clientMessageToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.entityToClientDTO;
import static com.poc.sg.domain.mapper.ClientMapper.entityToClientMessage;
import static com.poc.sg.factory.AddressFactory.createMessage;
import static com.poc.sg.factory.ClientFactory.createClient;
import static com.poc.sg.factory.ClientFactory.createClientDTO;
import static com.poc.sg.factory.ClientFactory.createMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientMapperTest {
    @Test
    void shouldMapperDtoToEntity() {
        final var clientDTO = createClientDTO();
        final var client = clientDTOToEntity(clientDTO);

        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getName(), client.getName());
        assertEquals(clientDTO.getDocument(), client.getDocument());
        assertEquals(clientDTO.getBirthDate(), client.getBirthDate().toString());
    }

    @Test
    void shouldMapperEntityToDto() {
        final var client = createClient();
        final var clientDTO = entityToClientDTO(client);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getDocument(), clientDTO.getDocument());
        assertEquals(client.getBirthDate().toString(), clientDTO.getBirthDate());
    }

    @Test
    void shouldMapperMessageToEntity() {
        final var addressMessage = createMessage();
        final var clientMessage = createMessage(addressMessage);
        final var client = clientMessageToEntity(clientMessage);

        assertNotNull(client.getAddressCharge());
        assertEquals(clientMessage.getId(), client.getId());
        assertEquals(clientMessage.getName(), client.getName());
        assertEquals(clientMessage.getDocument(), client.getDocument());
        assertEquals(clientMessage.getBirthDate(), client.getBirthDate().toString());
    }

    @Test
    void shouldMapperEntityToMessage() {
        final var client = createClient();
        final var clientMessage = entityToClientMessage(client);

        assertNotNull(clientMessage.getAddressCharge());
        assertEquals(client.getId(), clientMessage.getId());
        assertEquals(client.getName(), clientMessage.getName());
        assertEquals(client.getDocument(), clientMessage.getDocument());
        assertEquals(client.getBirthDate().toString(), clientMessage.getBirthDate());

    }
}
