package com.poc.sg.domain.mapper;

import com.poc.sg.domain.dto.ClientDTO;
import com.poc.sg.domain.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.poc.sg.config.TestConfig.getFaker;
import static com.poc.sg.domain.mapper.ClientMapper.clientDTOToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientMapperTest {
    @Test
    void shouldMapperDtoToEntity(){
        final var clientDTO = createClientDTO();
        final var client = clientDTOToEntity(clientDTO);

        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getDocument(), client.getDocument());
        assertEquals(clientDTO.getBirthDate(), client.getBirthDate());
    }

    @Test
    void shouldMapperEntityToDto(){

    }

    private Client createClient(){
        return new Client();
    }

    private ClientDTO createClientDTO(){
        return ClientDTO.builder()
                .birthDate(LocalDateTime.now().toString())
                .id(getFaker().number().randomNumber())
                .name(getFaker().name().name())
                .document(getFaker().numerify("074########"))

                .build();
    }

}