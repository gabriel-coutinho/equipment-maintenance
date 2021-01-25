package com.gabrielcoutinho.equipmentmaintenance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielcoutinho.equipmentmaintenance.domain.ServiceOrder;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer>{
	/**
	 * JPQL query to collect opens services orders
	 * @param pageRequest
	 * @return Page of opens services orders
	 */
	@Query("SELECT obj FROM ServiceOrder obj WHERE obj.open = 0")
	Page<ServiceOrder> getOpens(Pageable pageRequest);
}
