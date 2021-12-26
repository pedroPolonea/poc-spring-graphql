package com.poc.sg.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private long id;

    private String name;

    private String document;

    private String birthDate;

    private AddressDTO addressCharge;
}
