package com.poc.sg.controller;

import com.poc.sg.domain.entity.Invoice;
import com.poc.sg.service.InvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/invoice")
@Api(value = "Acme AP Fatura Service", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@ApiOperation(value = "Shows the list of invoices")
	@GetMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<?> getAllInvoice() {
		return ResponseEntity.ok(invoiceService.getAll());
	}

	@ApiOperation(value = "Query an invoice by code")
	@GetMapping(value = "/{code}", headers = "X-API-VERSION=1")
	public ResponseEntity<?> getInvoice(@PathVariable String code) {
		
		return ResponseEntity.ok(invoiceService.findByCode(code));
	}

	@ApiOperation(value = "Query invoices by customer document")
	@GetMapping(value = "/client/{document}", headers = "X-API-VERSION=1")
	public ResponseEntity<?> getInvoiceByDocument(@PathVariable String document) {

		return ResponseEntity.ok(invoiceService.findByDocument(document));
	}

	@ApiOperation(value = "Generate a new invoice")
	@PostMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {
		Invoice invoiceSave = invoiceService.save(invoice);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(invoiceSave.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
