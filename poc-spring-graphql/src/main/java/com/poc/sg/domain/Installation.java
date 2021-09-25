package com.poc.sg.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Installation {

	@Id
	@Column(name = "ID_INSTALLATION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DT_INSTALLATION", nullable = false)
	private LocalDateTime dataInstallation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CLIENT")
	private Client client;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ADDRESS")
	private Address addressInstallation;

	//@JsonIgnore
	@JsonBackReference
	@OneToMany(mappedBy = "installation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice> invoiceList = new ArrayList<Invoice>();


}
