package com.gabrielcoutinho.equipmentmaintenance.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceOrderOutputDto {
	private Integer id;
	private List<EventDto> events = new ArrayList<>();
	private ClientDto client;
	private EquipmentDto equipment;
	private String problem;
	private int closed;
}
