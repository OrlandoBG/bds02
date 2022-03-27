package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFound;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update (EventDTO dto, Long id) {
		try{
		Event event = repository.getOne(id);
	
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event.setCity (new City(dto.getCityId(), null));
		
		repository.save(event);
	
		return new EventDTO(event);
		
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFound("Resource not found " + id);
		}
		
	}
	
}
