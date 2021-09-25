package com.poc.sg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

	@Id
	@Column(name = "ID_INVOICE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DT_READING", nullable = false)
	private LocalDateTime readingDate;

	@Column(name = "DT_DUE", nullable = false)
	private LocalDateTime dueDate;

	@Column(name = "NUM_READING", nullable = false)
	private int numberReading;

	@Column(name = "ACCOUNT_VALUE", nullable = false)
	private BigDecimal accountValue;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_INSTALLATION")
	private Installation installation;

}
