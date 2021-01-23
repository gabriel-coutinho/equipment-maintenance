package com.gabrielcoutinho.equipmentmaintenance.dtos;

import java.time.LocalDateTime;

import com.gabrielcoutinho.equipmentmaintenance.enums.EventType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
	private Integer id;
	private EventType type;
	private Integer orderId;
	private String message;
	private LocalDateTime time;
}
