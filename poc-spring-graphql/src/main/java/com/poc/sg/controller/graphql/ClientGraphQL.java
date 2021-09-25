package com.poc.sg.controller.graphql;


import com.poc.sg.domain.Client;
import com.poc.sg.service.ClientService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ClientGraphQL implements GraphQLMutationResolver, GraphQLQueryResolver {
	
	@Autowired
	private ClientService clientService;


	public List<Client> getAllClient() {
		return clientService.getAll();
	}

	public Client getClientByDocument(@PathVariable String document){
		Optional<Client> optionalClient = clientService.findByDocument(document);

		if (optionalClient.isPresent()) {
			return optionalClient.get();
		}

		throw new GraphQLException("Client not found!");
	}

/*
	public List<Client> getAllClient() {
		return clientService.getAll();
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
*/
}
