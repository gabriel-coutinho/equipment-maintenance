package com.gabrielcoutinho.equipmentmaintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcoutinho.equipmentmaintenance.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
