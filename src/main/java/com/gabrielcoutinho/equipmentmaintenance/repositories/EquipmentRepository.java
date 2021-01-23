package com.gabrielcoutinho.equipmentmaintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcoutinho.equipmentmaintenance.domain.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{

}
