package com.gabrielcoutinho.equipmentmaintenance.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private Integer id;
	private String postalCode;
	private String streetName;
	private String number;
	private String complements;
	private String neighbourhood;
}
