package com.poc.sg.service;

import com.poc.sg.domain.Installation;

import java.util.List;

public interface InstallationService {

    List<Installation> getAll();

    Installation findByCode(String code);

    List<Installation> findByDocument(String document);

    Installation save(Installation installation);
}
