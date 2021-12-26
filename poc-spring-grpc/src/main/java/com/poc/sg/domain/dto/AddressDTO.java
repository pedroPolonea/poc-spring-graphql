package com.poc.sg.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private long id;

    private String address;

    private long number;

    private String district;

    private String city;

    private String federativeUnit;
}
