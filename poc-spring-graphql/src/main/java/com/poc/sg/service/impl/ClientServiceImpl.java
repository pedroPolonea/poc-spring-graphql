package com.poc.sg.service.impl;

import com.poc.sg.domain.Client;
import com.poc.sg.repository.ClientRepository;
import com.poc.sg.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        log.info("M=getAll");
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findByDocument(final String document) {
        log.info("M=findClientByDocument, document={}", document);

        return clientRepository.findByDocument(document);
    }

    @Override
    public Client findById(final Long id) {
        log.info("M=findById, id={}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("M=findClientByDocument, E=Client not found, document={}", id);
                    return new RuntimeException("Client not found "+id);
                });
    }

    @Override
    public Client save(final Client client) {
        return clientRepository.save(client);
    }
}
