package com.gabrielcoutinho.equipmentmaintenance.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;
import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;
import com.gabrielcoutinho.equipmentmaintenance.dtos.EventDto;
import com.gabrielcoutinho.equipmentmaintenance.dtos.ServiceOrderDto;
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
	
	/**
	 * 
	 * @param page [number of pages]
	 * @param linesPerPage [lines per page]
	 * @param orderBy [order by]
	 * @param direction [order direction]
	 * @return Page of services orders
	 */
	@GetMapping()
	public ResponseEntity<Page<ServiceOrderDto>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		log.info("Listing all services orders");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy.split(","));
		Page<ServiceOrder> orders = service.findAll(pageRequest);
		Page<ServiceOrderDto> ordersDto = orders.map(obj -> mapper.fromObj(obj));
		return ResponseEntity.ok(ordersDto);
	}
	
	/**
	 * List all open services orders
	 * @param page [number of pages]
	 * @param linesPerPage [lines per page]
	 * @param orderBy [order by]
	 * @param direction [order direction]
	 * @return Page of opens services orders
	 */
	@GetMapping("/opens")
	public ResponseEntity<Page<ServiceOrderDto>> getOpens(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		log.info("Listing all open services orders");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy.split(","));
		Page<ServiceOrder> orders = service.getOpens(pageRequest);
		Page<ServiceOrderDto> ordersDto = orders.map(obj -> mapper.fromObj(obj));
		return ResponseEntity.ok(ordersDto);
	}
	
	/**
	 * Find a service order by id
	 * @param id from service order
	 * @return Service Order
	 */
	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<ServiceOrderDto> findById(@PathVariable Integer id) {
		log.info("Searching service order by ID: {}", id);
		ServiceOrder resultOrder = service.findById(id);
		return ResponseEntity.ok(mapper.fromObj(resultOrder));
	}
	
	/**
	 * Persist in database a service order;
	 * Initial status - open
	 * @param orderDto service order to persist
	 * @return Persisted service order
	 */
	@PostMapping()
	public ResponseEntity<ServiceOrderDto> persist(@Valid @RequestBody ServiceOrderDto orderDto) {
		log.info("Persisting service order");
		ServiceOrder order = service.persist(orderDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.fromObj(order));
	}
	
	/**
	 * Delete in database a service order
	 * @param id from service order
	 * @return Void
	 */
	@DeleteMapping("/{id:[0-9]+}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		log.info("Deleting service order by ID: {}", id);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Update in database a service order
	 * @param orderDto service order to persist
	 * @param id from service order
	 * @return Void
	 */
	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<Void> update(@Valid @RequestBody ServiceOrderDto orderDto, @PathVariable Integer id) {
		log.info("Updating service order by ID: {}", id);
		orderDto.setId(id);
		service.update(orderDto);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Add a event on a service order by id
	 * @param eventDto event to persist
	 * @param id from service order
	 * @return Persisted event
	 */
	@PostMapping("/event/{id:[0-9]+}")
	public ResponseEntity<EventDto> addEvent(@Valid @RequestBody EventDto eventDto, @PathVariable Integer id) {
		log.info("Adding a event at service order ID: {}", id);
		eventDto.setOrderId(id);
		Event event = service.addEvent(eventDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/event/{id}")
				.buildAndExpand(event.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.fromObj(event));
	}
	
	/**
	 * Close a service order
	 * @param id from service order
	 * @return Void
	 */
	@PutMapping("/close/{id:[0-9]+}")
	public ResponseEntity<Void> closeOrder(@PathVariable Integer id) {
		log.info("Closing service order ID: {}", id);
		service.closeOrder(id);
		return ResponseEntity.noContent().build();
	}
}
