package com.gabrielcoutinho.equipmentmaintenance.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/orders")
@Slf4j
public class OrderServiceController {
	
	@GetMapping()
	public String list() {
		log.info("Listing all orders services");
		return "REST TEST!!";
	}
}
