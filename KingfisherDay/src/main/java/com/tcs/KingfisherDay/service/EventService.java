package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.model.enums.EventState;
import com.tcs.KingfisherDay.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	public List<Event> getAllEvents() {
		return eventRepository.findAllByOrderByEventID();
	}

	public List<Event> getCurrentEvent() {
		return eventRepository.findByState(EventState.RUNNING);
	}

	public void changeEventState(int eventID, String state) {
		List<Event> events = eventRepository.findByState(EventState.RUNNING);
		for (Event event : events) {
			event.setState(EventState.COMPLETED);
			eventRepository.save(event);
		}
		Event activeEvent = eventRepository.findByEventID(eventID);
		activeEvent.setState(EventState.valueOf(state));
		eventRepository.save(activeEvent);
	}

}