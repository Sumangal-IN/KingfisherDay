package com.tcs.KingfisherDay.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventService;

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@RequestMapping(value = "/getCurrentEvent", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Event getCurrentEvent() {
		return eventService.getCurrentEvent();
	}

	@RequestMapping(value = "/setCurrentEvent/{eventID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void setCurrentEvent(@PathVariable("eventID") String eventID) {
		eventService.setCurrentEvent(eventID);
	}

	@RequestMapping(value = "/clearCurrentEvent", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void clearCurrentEvent() {
		eventService.clearCurrentEvent();
	}
	
}