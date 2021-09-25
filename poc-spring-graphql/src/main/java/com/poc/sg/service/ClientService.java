package com.poc.sg.service;

import com.poc.sg.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAll();

    Optional<Client> findByDocument(String document);

    Client findById(Long id);

    Client save(Client client);
}
