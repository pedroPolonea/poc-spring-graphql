package com.poc.sg.domain.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.poc.sg.domain.mapper.ClientMapper.clientDTOToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.entityToClientDTO;
import static com.poc.sg.factory.ClientFactory.createClient;
import static com.poc.sg.factory.ClientFactory.createClientDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientMapperTest {
    @Test
    void shouldMapperDtoToEntity() {
        final var clientDTO = createClientDTO();
        final var client = clientDTOToEntity(clientDTO);

        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getDocument(), client.getDocument());
        assertEquals(clientDTO.getBirthDate(), client.getBirthDate().toString());
        assertEquals(clientDTO.getName(), client.getName());
    }

    @Test
    void shouldMapperEntityToDto() {
        final var client = createClient();
        final var clientDTO = entityToClientDTO(client);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getDocument(), clientDTO.getDocument());
        assertEquals(client.getBirthDate().toString(), clientDTO.getBirthDate());
        assertEquals(client.getName(), clientDTO.getName());
    }
}
