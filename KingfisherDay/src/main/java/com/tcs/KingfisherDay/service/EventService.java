package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public Event getCurrentEvent() {
		List<Event> activeEvents = eventRepository.findByCurrent(true);
		if (activeEvents.isEmpty())
			return null;
		return activeEvents.get(0);
	}

	public void setCurrentEvent(String eventID) {
		for (Event event : eventRepository.findAll()) {
			if (event.getEventID().equals(eventID))
				event.setCurrent(true);
			else
				event.setCurrent(false);
			eventRepository.save(event);
		}
	}

	public void clearCurrentEvent() {
		for (Event event : eventRepository.findAll()) {
			event.setCurrent(false);
			eventRepository.save(event);
		}
	}

}