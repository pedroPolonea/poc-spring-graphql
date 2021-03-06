package com.poc.sg.repository;

import com.poc.sg.domain.entity.Installation;
import com.poc.sg.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	Optional<Invoice> findByCode(String code);

	List<Invoice> findByInstallation(Installation installation);
}
