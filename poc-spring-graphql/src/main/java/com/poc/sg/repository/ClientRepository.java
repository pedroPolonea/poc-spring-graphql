package com.poc.sg.repository;

import com.poc.sg.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	public Optional<Client> findByDocument(final String document);
	
}
