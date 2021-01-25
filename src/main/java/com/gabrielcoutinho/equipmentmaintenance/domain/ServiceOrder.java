package com.gabrielcoutinho.equipmentmaintenance.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="orderService")
	private List<Event> events = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
	private Equipment equipment;
	private String problem;
	private int open;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + open;
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (open != other.open)
			return false;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (other.events != null) {
			for (Event event : events) {
				for (Event otherEvent : other.getEvents()) {
					if(!event.equals(otherEvent))
						return false;
				}
			}
		}
		if (problem == null) {
			if (other.problem != null)
				return false;
		} else if (!problem.equals(other.problem))
			return false;
		return true;
	}
}
