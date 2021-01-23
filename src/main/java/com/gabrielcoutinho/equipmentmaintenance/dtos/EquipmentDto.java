package com.gabrielcoutinho.equipmentmaintenance.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentDto {
	private Integer id;
	private String model;
	private String type;
}
