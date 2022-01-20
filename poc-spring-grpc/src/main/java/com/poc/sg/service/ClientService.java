package com.poc.sg.service;

import com.pb.proto.message.ClientMessage;
import com.poc.sg.domain.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    List<Client> getAll(ClientMessage clientMessage);

    Client findByDocument(String document);

    Client findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void delete(Long id);
}
