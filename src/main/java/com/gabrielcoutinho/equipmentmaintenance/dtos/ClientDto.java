package com.gabrielcoutinho.equipmentmaintenance.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDto {
	private Integer id;
	private AddressDto address;
	private String email;
	private String phone;
}
