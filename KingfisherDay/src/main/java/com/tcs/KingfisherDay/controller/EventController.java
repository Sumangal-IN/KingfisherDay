package com.tcs.KingfisherDay.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.KingfisherDay.model.Event;
import com.tcs.KingfisherDay.service.EventService;
import com.tcs.KingfisherDay.service.ImageHandlingService;

@Controller
public class EventController {

	@Autowired
	EventService eventService;
	@Autowired
	ImageHandlingService imageHandlingService;

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@RequestMapping(value = "/getEventById/{eventId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Event getEventById(@PathVariable("eventId") String eventId) {
		return eventService.getEventById(eventId);
	}

	@RequestMapping(value = "/registerEvent/{eventName}/{details}/{startDate}/{endDate}/{startTime}/{duration}/{photo}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Event registerEvent(
			@PathVariable("eventName") String name, 
			@PathVariable("details") String details,
			@PathVariable("startDate") String startDate, 
			@PathVariable("endDate") String endDate,
			@PathVariable("startTime") String startTime,
			@PathVariable("duration") String duration, 
			@RequestParam("photo") MultipartFile photoFile) {
		Event event=null;
		try {
			String photo = imageHandlingService.resizeImage(photoFile);
			event=eventService.registerAnEvent(name, details, startDate, endDate, startTime, duration, photo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return event;
	}

}
