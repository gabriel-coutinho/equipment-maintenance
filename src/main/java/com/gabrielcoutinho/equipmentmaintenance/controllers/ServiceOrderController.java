package com.gabrielcoutinho.equipmentmaintenance.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderInputDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderOutputDto;
import com.gabrielcoutinho.equipmentmaintenance.services.ServiceOrderService;
import com.gabrielcoutinho.equipmentmaintenance.utils.Mapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/orders")
@Slf4j
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderService service;
	private Mapper mapper = new Mapper();
	
//	@GetMapping()
//	public ResponseEntity<String> findAll() {
//		log.info("Listing all orders services");
//		return ResponseEntity.ok("");
//	}
	
	@GetMapping()
	public ResponseEntity<Page<ServiceOrderOutputDto>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		log.info("Listing all orders services");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy.split(","));
		Page<ServiceOrder> orders = service.findAll(pageRequest);
		Page<ServiceOrderOutputDto> ordersDto = orders.map(obj -> mapper.fromObj(obj));
		return ResponseEntity.ok(ordersDto);
	}
	
	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<ServiceOrderOutputDto> findById(@PathVariable Integer id) {
		log.info("Searching order service by ID: {}", id);
		ServiceOrder resultOrder = service.findById(id);
		return ResponseEntity.ok(mapper.fromObj(resultOrder));
	}
	
	@PostMapping()
	public ResponseEntity<ServiceOrderOutputDto> persist(@Valid @RequestBody ServiceOrderInputDto orderInputDto) {
		ServiceOrder order = service.persist(orderInputDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.fromObj(order));
	}
}
