package com.gabrielcoutinho.equipmentmaintenance.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;
import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.EventDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderDto;
import com.gabrielcoutinho.equipmentmaintenance.repositories.ServiceOrderRepository;
import com.gabrielcoutinho.equipmentmaintenance.services.EventService;
import com.gabrielcoutinho.equipmentmaintenance.services.ServiceOrderService;
import com.gabrielcoutinho.equipmentmaintenance.services.exceptions.ObjectNotFoundException;
import com.gabrielcoutinho.equipmentmaintenance.utils.Mapper;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService{
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ServiceOrderRepository orderRepo;
	private Mapper mapper = new Mapper();
	
	@Override
	public Page<ServiceOrder> findAll(PageRequest pageable) {
		return orderRepo.findAll(pageable);
	}

	@Override
	public ServiceOrder persist(ServiceOrderDto orderDto) {
		ServiceOrder serviceOrder = mapper.fromDto(orderDto);
		serviceOrder.setId(null);
		return orderRepo.save(serviceOrder);
	}
	
	@Override
	public ServiceOrder findById(Integer id) {
		Optional<ServiceOrder> obj = orderRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + ServiceOrder.class.getName()));
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		orderRepo.deleteById(id);
	}

	@Override
	public void update(ServiceOrderDto orderDto) {
		findById(orderDto.getId());
		orderRepo.save(mapper.fromDto(orderDto));
	}

	@Override
	public Event addEvent(EventDto eventDto) {
		ServiceOrder order = findById(eventDto.getOrderId());
		Event event = mapper.fromDto(eventDto);
		event.setOrderService(order);
		event.setTime(LocalDateTime.now());
		return eventService.persist(event);
	}

	@Override
	public Page<ServiceOrder> getOpens(PageRequest pageRequest) {
		return orderRepo.getOpens(pageRequest);
	}

	@Override
	public void closeOrder(Integer id) {
		ServiceOrder order = findById(id);
		order.setOpen(1);
		orderRepo.save(order);
	}
}
