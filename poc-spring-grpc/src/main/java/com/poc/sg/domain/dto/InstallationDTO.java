package com.poc.sg.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallationDTO {

    private long id;

    private String code;

    private String dataInstallation;

    private ClientDTO client;

    private AddressDTO addressInstallation;

    private List<InvoiceDTO> invoiceList;
}
