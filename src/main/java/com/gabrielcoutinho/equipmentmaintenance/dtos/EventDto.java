package com.gabrielcoutinho.equipmentmaintenance.dtos;

import java.time.LocalDateTime;

import com.gabrielcoutinho.equipmentmaintenance.enums.EventType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
	private Integer id;
	private EventType type;
	private String message;
	private LocalDateTime time;
}
