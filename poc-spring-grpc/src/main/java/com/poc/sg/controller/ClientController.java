package com.poc.sg.controller;

import com.poc.sg.domain.entity.Client;
import com.poc.sg.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/client")
@Api(value = "XPTO client Service")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@ApiOperation(value = "Displays the list of customers")
	@GetMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<?> getAllClient() {
		return ResponseEntity.ok(clientService.getAll());
	}

	@ApiOperation(value = "Consult a customer by document")
	@GetMapping(value = "/{document}", headers = "X-API-VERSION=1")
	public ResponseEntity<?> getClientByDocument(@PathVariable String document){
		return ResponseEntity.ok(clientService.findByDocument(document));
	}

	@ApiOperation(value = "Register a new customer")
	@PostMapping(headers = "X-API-VERSION=1")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createClient(@RequestBody Client client){
		log.info("M=createClient, client={}", client);

		Client clientSave = clientService.save(client);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientSave.getId()).toUri();

		log.info("M=createClient, clientSave={}", clientSave);
		return ResponseEntity.created(location).build();
	}

}
