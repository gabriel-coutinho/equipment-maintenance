package com.gabrielcoutinho.equipmentmaintenance.utils;

import java.util.stream.Collectors;

import com.gabrielcoutinho.equipmentmaintenance.domain.Address;
import com.gabrielcoutinho.equipmentmaintenance.domain.Client;
import com.gabrielcoutinho.equipmentmaintenance.domain.Equipment;
import com.gabrielcoutinho.equipmentmaintenance.domain.Event;
import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.AddressDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ClientDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.EquipmentDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.EventDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderDto;

/**
 * Map entities from dtos and dtos from entities
 * @author gabri
 *
 */
public class Mapper {
	
	public Address fromDto(AddressDto dto) {
		return new Address(dto.getId(), dto.getPostalCode(), dto.getStreetName(), dto.getNumber(), dto.getComplements(), dto.getNeighbourhood());
	}
	
	public AddressDto fromObj(Address obj) {
		AddressDto dto = new AddressDto();
		dto.setId(obj.getId());
		dto.setPostalCode(obj.getPostalCode());
		dto.setStreetName(obj.getStreetName());
		dto.setNumber(obj.getNumber());
		dto.setComplements(obj.getComplements());
		dto.setNeighbourhood(obj.getNeighbourhood());
		return dto;
	}

	public Client fromDto(ClientDto dto) {
		Address address = fromDto(dto.getAddress());
		return new Client(dto.getId(), address, dto.getEmail(), dto.getPhone());
	}
	
	public ClientDto fromObj(Client obj) {
		ClientDto dto = new ClientDto();
		dto.setId(obj.getId());
		dto.setAddress(fromObj(obj.getAddress()));
		dto.setEmail(obj.getEmail());
		dto.setPhone(obj.getPhone());
		return dto;
	}
	
	public Equipment fromDto(EquipmentDto dto) {
		return new Equipment(dto.getId(), dto.getModel(), dto.getType());
	}
	
	public EquipmentDto fromObj(Equipment obj) {
		EquipmentDto dto = new EquipmentDto();
		dto.setId(obj.getId());
		dto.setModel(obj.getModel());
		dto.setType(obj.getType());
		return dto;
	}
	
	public Event fromDto(EventDto dto) {
		return new Event(dto.getId(), null, dto.getType(), dto.getMessage(), dto.getTime());
	}
	
	public EventDto fromObj(Event obj) {
		EventDto dto = new EventDto();
		dto.setId(obj.getId());
		dto.setMessage(obj.getMessage());
		dto.setType(obj.getType());
		dto.setTime(obj.getTime());
		dto.setOrderId(obj.getOrderService().getId());
		return dto;
	}
	
	public ServiceOrder fromDto(ServiceOrderDto dto) {
		ServiceOrder obj = new ServiceOrder();
		Client client = fromDto(dto.getClient());
		Equipment equipment = fromDto(dto.getEquipment());
		
		if(dto.getEvents() != null) {
			obj.setEvents(dto.getEvents().stream().map(event -> fromDto(event)).collect(Collectors.toList()));
			obj.getEvents().forEach(event -> event.setOrderService(obj));
		}
		
		obj.setId(dto.getId());
		obj.setOpen(dto.getOpen());
		obj.setClient(client);
		obj.setEquipment(equipment);
		obj.setProblem(dto.getProblem());
		return obj;
	}
	
	public ServiceOrderDto fromObj(ServiceOrder obj) {
		ServiceOrderDto dto = new ServiceOrderDto();
		ClientDto clientDto = fromObj(obj.getClient());
		EquipmentDto equipmentDto = fromObj(obj.getEquipment());
		
		if(obj.getEvents() != null) {
			dto.setEvents(obj.getEvents().stream().map(event -> fromObj(event)).collect(Collectors.toList()));
			dto.getEvents().forEach(event -> event.setOrderId(obj.getId()));
		}
		
		dto.setId(obj.getId());
		dto.setOpen(obj.getOpen());
		dto.setClient(clientDto);
		dto.setEquipment(equipmentDto);
		dto.setProblem(obj.getProblem());
		return dto;
	}
}
