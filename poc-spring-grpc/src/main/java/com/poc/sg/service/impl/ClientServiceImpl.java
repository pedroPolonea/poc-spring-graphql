package com.poc.sg.service.impl;

import com.pb.proto.message.ClientMessage;
import com.poc.sg.domain.entity.Client;
import com.poc.sg.exception.NotFoundException;
import com.poc.sg.repository.ClientRepository;
import com.poc.sg.repository.specification.ClientSpecification;
import com.poc.sg.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

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
    public List<Client> getAll(final ClientMessage clientMessage) {
        log.info("M=getAll, clientMessage={}", clientMessage);
        return clientRepository.findAll(where(ClientSpecification.getClientSpecification(clientMessage)));
    }

    @Override
    public Client findByDocument(final String document) {
        log.info("M=findClientByDocument, document={}", document);

        return clientRepository.findByDocument(document)
                .orElseThrow(() -> {
                    log.error("M=findClientByDocument, E=Client not found, document={}", document);
                    return new NotFoundException(String.valueOf(document));
                });
    }

    @Override
    public Client findById(final Long id) {
        log.info("M=findById, id={}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("M=findClientByDocument, E=Client not found, document={}", id);
                    return new NotFoundException(String.valueOf(id));
                });
    }

    @Override
    public Client save(final Client client) {
        log.info("M=save, I=Inicio, client={}", client);
        return clientRepository.save(client);
    }

    @Override
    public Client update(final Client client) {
        log.info("M=update, I=Inicio, client={}", client);
        Optional<Client> clientOptional = clientRepository.findById(client.getId());

        clientOptional.orElseThrow(() ->  {
            log.error("M=update, E=Cliente não encontrado,  client={}", client);
            return new NotFoundException(String.valueOf(client.getId()));
        });

        Client clientBase = clientOptional.get();
        log.info("M=update, I=Pre-copia, client={}, clientBase={}", client, clientBase);
        BeanUtils.copyProperties(client, clientBase);
        log.info("M=update, I=Pos-copia, client={}, clientBase={}", client, clientBase);

        return  clientRepository.save(clientBase);
    }

    @Override
    public void delete(final Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        clientOptional.ifPresent(client -> clientRepository.delete(client));
        clientOptional.orElseThrow(() ->  {
            log.error("M=update, E=Cliente não encontrado,  idClient={}", id);
            return new NotFoundException(String.valueOf(id));
        });
    }
}
