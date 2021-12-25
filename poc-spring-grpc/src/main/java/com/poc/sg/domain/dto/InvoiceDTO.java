package com.poc.sg.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private long id;

    private String code;

    private String readingDate;

    private String dueDate;

    private int numberReading;

    private BigDecimal accountValue;

    private InstallationDTO installation;
}
