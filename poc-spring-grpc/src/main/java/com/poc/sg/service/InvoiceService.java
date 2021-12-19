package com.poc.sg.service;

import com.poc.sg.domain.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAll();

    Invoice findByCode(String code);

    List<Invoice> findByDocument(String document);

    Invoice save(Invoice invoice);
}
