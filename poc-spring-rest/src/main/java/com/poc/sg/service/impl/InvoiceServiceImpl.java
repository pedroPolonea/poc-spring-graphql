package com.poc.sg.service.impl;

import com.poc.sg.domain.Installation;
import com.poc.sg.domain.Invoice;
import com.poc.sg.repository.InvoiceRepository;
import com.poc.sg.service.InstallationService;
import com.poc.sg.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InstallationService installationService;

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findByCode(final String code) {
        log.info("M=findByCode, code={}", code);
        return invoiceRepository.findByCode(code)
                .orElseThrow(() -> {
                    log.error("M=findByCode, E=Invoice not found, code={}", code);
                    return new RuntimeException("Invoice not found "+code);
                });
    }

    @Override
    public List<Invoice> findByDocument(String document) {
        final List<Installation> installations = installationService.findByDocument(document);
        List<Invoice> invoiceList = new ArrayList<Invoice>();

        installations.stream()
                .map(Installation::getInvoiceList)
                .forEach(invoices -> {
                    invoices.stream()
                            .forEach(invoice -> {
                                invoiceList.add(invoice);
                             });
                });
        return invoiceList;
    }

    @Override
    public Invoice save(Invoice invoice) {
        final Installation installation = installationService.findByCode(invoice.getInstallation().getCode());
        invoice.setInstallation(installation);
        installation.getInvoiceList().add(invoice);

        return invoiceRepository.save(invoice);
    }
}
