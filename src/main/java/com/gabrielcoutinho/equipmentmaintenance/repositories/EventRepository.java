package com.gabrielcoutinho.equipmentmaintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcoutinho.equipmentmaintenance.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
