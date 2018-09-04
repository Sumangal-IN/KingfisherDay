package com.tcs.KingfisherDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	QuestionService questionService;
	@Autowired
	ResponseService responseService;

	@RequestMapping(value = "/setCurrentQuestion/{questionID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void setCurrentQuestion(@PathVariable("questionID") String questionID) {
		questionService.updateCurrentQuestion(questionID);
	}

	@RequestMapping(value = "/getCurrentQuestion", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Question getCurrentQuestion() {
		return questionService.getCurrentQuestion();
	}

	@RequestMapping(value = "/saveResponse/{questionID}/{employeeEmail}/{option}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void saveResponse(@PathVariable("questionID") String questionID,
			@PathVariable("employeeEmail") String employeeEmail, @PathVariable("option") String option) {
		responseService.saveResponse(questionID, employeeEmail, option);
	}

	@RequestMapping(value = "/getStat/{questionID}/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void getStat(@PathVariable("questionID") String questionID) {
		
	}

}
