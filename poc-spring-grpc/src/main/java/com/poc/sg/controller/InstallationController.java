package com.poc.sg.controller;


import com.poc.sg.domain.entity.Installation;
import com.poc.sg.service.ClientService;
import com.poc.sg.service.InstallationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/installation")
@Api(value = "XPTO Installation Service")
public class InstallationController {
	
	@Autowired
	private InstallationService installationService;
	
	@Autowired
	private ClientService clientService;
	
	
	@ApiOperation(value = "Shows the list of installations")
	@GetMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<?> getAllInstallation(){
		return ResponseEntity.ok(installationService.getAll());
	}
	
	
	@ApiOperation(value = "Query an installation by code")
	@GetMapping(value = "/{code}", headers = "X-API-VERSION=1")
	public ResponseEntity<?> getInstallation(@PathVariable String code) {
		return ResponseEntity.ok(installationService.findByCode(code));
	}
	
	@ApiOperation(value = "Query an installation by CPF")
	@GetMapping(value = "/client/{document}", headers = "X-API-VERSION=1")
	public ResponseEntity<?> getInstallationByDocument(@PathVariable String document){
		return ResponseEntity.ok(installationService.findByDocument(document));
	}
	
	@ApiOperation(value = "Register a new installation")
	@PostMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<?> createInstallation(@RequestBody Installation installation){

		Installation installationSave = installationService.save(installation);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(installationSave.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	
	
}
