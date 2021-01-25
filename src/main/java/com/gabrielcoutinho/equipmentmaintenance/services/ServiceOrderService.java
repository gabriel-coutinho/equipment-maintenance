package com.gabrielcoutinho.equipmentmaintenance.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;
import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.EventDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderDto;

public interface ServiceOrderService {

	Page<ServiceOrder> findAll(PageRequest pageRequest);
	
	ServiceOrder findById(Integer id);
	
	ServiceOrder persist(ServiceOrderDto orderDto);
	
	void delete(Integer id);
	
	void update(ServiceOrderDto orderDto);
	
	Event addEvent(EventDto eventDto);

	Page<ServiceOrder> getOpens(PageRequest pageRequest);
	
	void closeOrder(Integer id);
}
