package com.gabrielcoutinho.equipmentmaintenance.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;
import com.gabrielcoutinho.equipmentmaintenance.repositories.EventRepository;
import com.gabrielcoutinho.equipmentmaintenance.services.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository repo;
	
	@Override
	public List<Event> persistEvents(List<Event> events) {
		return repo.saveAll(events);
	}

	@Override
	public Event persist(Event event) {
		return repo.save(event);
	}

}
