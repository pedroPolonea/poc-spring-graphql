package com.poc.sg.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@Column(name = "ID_ADDRESS")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "NUMBER", nullable = false)
	private long number;

	@Column(name = "DISTRICT", nullable = false)
	private String district;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "FEDERATIVE_UNIT", nullable = false)
	private String federativeUnit;

}
