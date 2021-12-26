package com.poc.sg.domain.mapper;


import com.poc.sg.domain.dto.InstallationDTO;
import com.poc.sg.domain.entity.Installation;

import static com.poc.sg.domain.mapper.AddressMapper.addressDTOToEntity;
import static com.poc.sg.domain.mapper.AddressMapper.entityToAddressDTO;
import static com.poc.sg.domain.mapper.ClientMapper.clientDTOToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.entityToClientDTO;
import static java.time.LocalDateTime.parse;

public class InstallationMapper {

    public static InstallationDTO entityToInstallationDTO(final Installation installation) {
        return InstallationDTO.builder()
                .addressInstallation(entityToAddressDTO(installation.getAddressInstallation()))
                .client(entityToClientDTO(installation.getClient()))
                .dataInstallation(installation.getDataInstallation().toString())
                .code(installation.getCode())
                .build();
    }

    public static Installation installationDTOToEntity(final InstallationDTO installationDTO) {
        return new Installation(
                installationDTO.getId(),
                installationDTO.getCode(),
                parse(installationDTO.getDataInstallation()),
                clientDTOToEntity(installationDTO.getClient()),
                addressDTOToEntity(installationDTO.getAddressInstallation()),
                null
        );
    }
}
