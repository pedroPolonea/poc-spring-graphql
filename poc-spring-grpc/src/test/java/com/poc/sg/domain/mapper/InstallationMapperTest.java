package com.poc.sg.domain.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static com.poc.sg.domain.mapper.InstallationMapper.entityToInstallationDTO;
import static com.poc.sg.domain.mapper.InstallationMapper.installationDTOToEntity;
import static com.poc.sg.factory.InstallationFactory.createInstallation;
import static com.poc.sg.factory.InstallationFactory.createInstallationDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstallationMapperTest {

    @Test
    void shouldMapperDtoToEntity() {
        final var installationDTO = createInstallationDTO();
        final var installation = installationDTOToEntity(installationDTO);

        assertEquals(installation.getCode(), installationDTO.getCode());
        assertEquals(installation.getId(), installationDTO.getId());
        assertEquals(installation.getDataInstallation(), LocalDateTime.parse(installationDTO.getDataInstallation()));
    }

    @Test
    void shouldMapperEntityToDto() {
        final var installation = createInstallation();
        final var installationDTO = entityToInstallationDTO(installation);

        assertEquals(installation.getCode(), installationDTO.getCode());
        assertEquals(installation.getId(), installationDTO.getId());
        assertEquals(installation.getDataInstallation().toString(), installationDTO.getDataInstallation());
    }
}
