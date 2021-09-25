package com.poc.sg.service.impl;

import com.poc.sg.domain.Client;
import com.poc.sg.domain.Installation;
import com.poc.sg.repository.InstallationRepository;
import com.poc.sg.service.ClientService;
import com.poc.sg.service.InstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InstallationServiceImpl implements InstallationService {

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public List<Installation> getAll() {
        return installationRepository.findAll();
    }

    @Override
    public Installation findByCode(String code) {
        log.info("M=findClientByDocument, code={}", code);

        return installationRepository.findByCode(code)
                .orElseThrow(() -> {
                    log.error("M=findClientByDocument, E=Installation not found, code={}", code);
                    return new RuntimeException("Installation not found "+code);
                });
    }

    @Override
    public List<Installation> findByDocument(String document) {
        log.info("M=findInstallationByDocument, document={}", document);
        Optional<Client> optionalClient = clientService.findByDocument(document);

        return installationRepository.findByClient(optionalClient.get());
    }

    @Override
    public Installation save(Installation installation) {
        log.info("M=save, installation={}", installation);

        final Client client = clientService.findById(installation.getClient().getId());
        installation.setClient(client);

        return installationRepository.save(installation);
    }
}
