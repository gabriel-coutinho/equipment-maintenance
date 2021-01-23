package com.gabrielcoutinho.equipmentmaintenance.services;

import java.util.List;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;

public interface EventService {
	List<Event> persistEvents(List<Event> events);
}
