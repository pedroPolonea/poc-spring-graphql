package com.poc.sg.service;

import com.poc.sg.domain.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client findByDocument(String document);

    Client findById(Long id);

    Client save(Client client);
}
