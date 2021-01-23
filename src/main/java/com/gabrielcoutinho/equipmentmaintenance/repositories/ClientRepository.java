package com.gabrielcoutinho.equipmentmaintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcoutinho.equipmentmaintenance.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
