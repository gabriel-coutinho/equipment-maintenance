package com.gabrielcoutinho.equipmentmaintenance.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gabrielcoutinho.equipmentmaintenance.domain.Address;
import com.gabrielcoutinho.equipmentmaintenance.domain.Client;
import com.gabrielcoutinho.equipmentmaintenance.domain.Equipment;
import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderInputDto;
import com.gabrielcoutinho.equipmentmaintenance.repositories.AddressRepository;
import com.gabrielcoutinho.equipmentmaintenance.repositories.ClientRepository;
import com.gabrielcoutinho.equipmentmaintenance.repositories.EquipmentRepository;
import com.gabrielcoutinho.equipmentmaintenance.repositories.ServiceOrderRepository;
import com.gabrielcoutinho.equipmentmaintenance.services.EventService;
import com.gabrielcoutinho.equipmentmaintenance.services.ServiceOrderService;
import com.gabrielcoutinho.equipmentmaintenance.services.exceptions.ObjectNotFoundException;
import com.gabrielcoutinho.equipmentmaintenance.utils.Mapper;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService{
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
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
	public ServiceOrder persist(ServiceOrderInputDto orderInputDto) {
		ServiceOrder serviceOrder = mapper.fromInputDto(orderInputDto);
		return save(serviceOrder);
	}
	
	private ServiceOrder save(ServiceOrder order) {
		if(order.getClient() != null) {
			if(order.getClient().getAddress() != null) {
				Address address = addressRepo.save(order.getClient().getAddress());
				order.getClient().setAddress(address);
			}
			Client client = clientRepo.save(order.getClient());
			order.setClient(client);
		} 
		if(order.getEquipment() != null) {
			Equipment equipment = equipmentRepo.save(order.getEquipment());
			order.setEquipment(equipment);
		}
		ServiceOrder result = orderRepo.save(order);
		if(order.getEvents() != null) {
			order.getEvents().forEach(event -> event.setTime(LocalDateTime.now()));
			eventService.persistEvents(order.getEvents());
		}
		return result;
	}

	@Override
	public ServiceOrder findById(Integer id) {
		Optional<ServiceOrder> obj = orderRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ServiceOrder.class.getName()));
	}
}
