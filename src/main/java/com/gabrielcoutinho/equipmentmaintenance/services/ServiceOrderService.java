package com.gabrielcoutinho.equipmentmaintenance.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderInputDto;

public interface ServiceOrderService {

	Page<ServiceOrder> findAll(PageRequest pageable);
	
	ServiceOrder findById(Integer id);
	
	ServiceOrder persist(ServiceOrderInputDto orderInputDto);
}
