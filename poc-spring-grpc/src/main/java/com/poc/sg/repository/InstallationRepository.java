package com.poc.sg.repository;

import com.poc.sg.domain.entity.Client;
import com.poc.sg.domain.entity.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {

	Optional<Installation> findByCode(String code);

	List<Installation> findByClient(Client client);
}
