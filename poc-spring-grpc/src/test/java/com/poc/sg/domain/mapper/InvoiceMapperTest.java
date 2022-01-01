package com.poc.sg.domain.mapper;

import com.poc.sg.factory.InvoiceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static com.poc.sg.domain.mapper.InvoiceMapper.invoiceDTOToEntity;
import static com.poc.sg.factory.InvoiceFactory.createInvoiceDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceMapperTest {

    @Test
    void shouldMapperDtoToEntity() {
        final var invoiceDTO = createInvoiceDTO();
        final var invoice = invoiceDTOToEntity(invoiceDTO);

        assertEquals(invoiceDTO.getCode(), invoice.getCode());
        assertEquals(invoiceDTO.getAccountValue(), invoice.getAccountValue());
        assertEquals(invoiceDTO.getNumberReading(), invoice.getNumberReading());
        assertEquals(LocalDateTime.parse(invoiceDTO.getReadingDate()), invoice.getReadingDate());
        assertEquals(LocalDateTime.parse(invoiceDTO.getDueDate()), invoice.getDueDate());
    }

    @Test
    void shouldMapperEntityToDto() {
        final var invoice = InvoiceFactory.createInvoice();
        final var invoiceDTO = InvoiceMapper.entityToInvoiceDTO(invoice);

        assertEquals(invoice.getCode(), invoiceDTO.getCode());
        assertEquals(invoice.getAccountValue(), invoiceDTO.getAccountValue());
        assertEquals(invoice.getNumberReading(), invoiceDTO.getNumberReading());
        assertEquals(invoice.getReadingDate().toString(), invoiceDTO.getReadingDate());
        assertEquals(invoice.getDueDate().toString(), invoiceDTO.getDueDate());
    }
}
