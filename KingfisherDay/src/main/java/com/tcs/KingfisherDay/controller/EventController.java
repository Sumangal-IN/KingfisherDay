package com.tcs.KingfisherDay.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.service.EventResponseService;
import com.tcs.KingfisherDay.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventService;

	@Autowired
	EventResponseService eventResponseService;

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

	@RequestMapping(value = "/changeEventState/{eventID}/{state}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void changeEventState(@PathVariable("eventID") int eventID, @PathVariable("state") String state) {
		eventService.changeEventState(eventID, state);
	}

	@RequestMapping(value = "/saveEventResponse/{emailID}/{eventID}/{vote}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void saveEventResponse(@PathVariable("emailID") String emailID, @PathVariable("eventID") String eventID,
			@PathVariable("vote") String vote) {
		eventResponseService.save(emailID, eventID, vote);
	}

	@RequestMapping(value = "/saveEventResponseWithComment/{emailID}/{eventID}/{vote}/{comment}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void saveEventResponseWithComment(@PathVariable("emailID") String emailID,
			@PathVariable("eventID") String eventID, @PathVariable("vote") String vote,
			@PathVariable("comment") String comment) {
		eventResponseService.saveWithComment(emailID, eventID, vote, comment);
	}

}