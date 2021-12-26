package com.poc.sg.factory;

import com.poc.sg.domain.dto.InvoiceDTO;
import com.poc.sg.domain.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.poc.sg.config.TestConfig.getFaker;
import static com.poc.sg.factory.InstallationFactory.createInstallation;
import static com.poc.sg.factory.InstallationFactory.createInstallationDTO;

public class InvoiceFactory {

    public static InvoiceDTO createInvoiceDTO(){
        return InvoiceDTO.builder()
                .readingDate(LocalDateTime.now().toString())
                .dueDate(LocalDateTime.now().toString())
                .id(getFaker().number().randomNumber())
                .code(getFaker().numerify("90###09"))
                .numberReading(getFaker().number().randomDigit())
                .installation(createInstallationDTO())
                .accountValue(new BigDecimal(1000))
                .build();
    }

    public static Invoice createInvoice(){
        return new Invoice(
                getFaker().number().randomNumber(),
                getFaker().numerify("90###09"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                getFaker().number().randomDigit(),
                new BigDecimal(1000),
                createInstallation()
        );
    }
}
