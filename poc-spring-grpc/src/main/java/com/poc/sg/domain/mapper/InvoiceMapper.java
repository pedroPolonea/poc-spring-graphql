package com.poc.sg.domain.mapper;

import com.poc.sg.domain.dto.InvoiceDTO;
import com.poc.sg.domain.entity.Invoice;

import static com.poc.sg.domain.mapper.InstallationMapper.entityToInstallationDTO;
import static com.poc.sg.domain.mapper.InstallationMapper.installationDTOToEntity;
import static java.time.LocalDateTime.parse;

public class InvoiceMapper {

    public static Invoice invoiceDTOToEntity(final InvoiceDTO invoiceDTO){
        return new Invoice(
                invoiceDTO.getId(),
                invoiceDTO.getCode(),
                parse(invoiceDTO.getReadingDate()),
                parse(invoiceDTO.getDueDate()),
                invoiceDTO.getNumberReading(),
                invoiceDTO.getAccountValue(),
                installationDTOToEntity(invoiceDTO.getInstallation())
        );
    }

    public static InvoiceDTO entityToInvoiceDTO(final Invoice invoice){
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .dueDate(invoice.getDueDate().toString())
                .readingDate(invoice.getReadingDate().toString())
                .accountValue(invoice.getAccountValue())
                .code(invoice.getCode())
                .installation(entityToInstallationDTO(invoice.getInstallation()))
                .numberReading(invoice.getNumberReading())
                .build();
    }
}
