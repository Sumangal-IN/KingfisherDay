package com.tcs.KingfisherDay.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.model.FoodPreference;
import com.tcs.KingfisherDay.model.Question;
import com.tcs.KingfisherDay.model.Response;
import com.tcs.KingfisherDay.service.QuestionService;
import com.tcs.KingfisherDay.service.RegistrationService;
import com.tcs.KingfisherDay.service.ResponseService;

@RestController
public class AppController {

	@Autowired
	QuestionService questionService;
	@Autowired
	ResponseService responseService;
	@Autowired
	RegistrationService registrationService;

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

	@RequestMapping(value = "/getResult/{questionID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Response getResult(@PathVariable("questionID") String questionID) {
		Response winnerResponse = responseService.getWinner(questionID, questionService.getQuestion(questionID));
		return winnerResponse;
	}
	
	@RequestMapping(value = "/registerEmployee/{employeeName}/{employeeEmail}/{foodPreference}/{photoPath}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void registerEmployee(@PathVariable("employeeName") String employeeName,
			@PathVariable("employeeEmail") String employeeEmail, @PathVariable("foodPreference") String foodPreference, @PathVariable("photoPath") String photoPath) {
		if(!(StringUtils.isEmpty(employeeName) && StringUtils.isEmpty(employeeEmail) && StringUtils.isEmpty(foodPreference))) {
			Employee employee=new Employee(Math.abs(new Random().nextInt(2222222)),employeeName, employeeEmail, FoodPreference.valueOf(foodPreference), photoPath);
			System.out.println(employee);
			registrationService.registerEmployee(employee);
		}
		
	}

}
