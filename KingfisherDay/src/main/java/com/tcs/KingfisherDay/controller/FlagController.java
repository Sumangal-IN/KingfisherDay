package com.tcs.KingfisherDay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tcs.KingfisherDay.model.Flag;
import com.tcs.KingfisherDay.service.FlagService;

@Controller
public class FlagController {

	@Autowired
	FlagService flagService;

	@RequestMapping(value = "/getAllFlags", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Flag> getAllFlags() {
		return flagService.getAllFlags();
	}
	
	@RequestMapping(value = "/delay", method = RequestMethod.GET, produces = "application/data")
	@ResponseBody
	public String delay() throws InterruptedException {
		Thread.sleep(5000);
		return "abcd";
	}

	@RequestMapping(value = "/updateFlag/{key}/{value}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Flag updateFlag(@PathVariable("key") String key, @PathVariable("value") String value) {
		return flagService.updateFlag(key, Boolean.valueOf(value));
	}

}
