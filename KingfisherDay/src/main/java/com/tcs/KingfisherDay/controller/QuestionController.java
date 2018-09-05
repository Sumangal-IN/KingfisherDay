package com.tcs.KingfisherDay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.model.OptionPercentage;
import com.tcs.KingfisherDay.model.Question;
import com.tcs.KingfisherDay.model.QuizResult;
import com.tcs.KingfisherDay.model.Response;
import com.tcs.KingfisherDay.service.QuestionService;
import com.tcs.KingfisherDay.service.ResponseService;
import com.tcs.KingfisherDay.service.UserService;

@RestController
@SessionAttributes("name")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	@Autowired
	ResponseService responseService;
	@Autowired
	UserService userService;

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
	public Response saveResponse(@PathVariable("questionID") String questionID,
			@PathVariable("employeeEmail") String employeeEmail, @PathVariable("option") String option) {
		return responseService.saveResponse(questionID, employeeEmail, option);
	}

	@RequestMapping(value = "/getResult/{questionID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public QuizResult getResult(@PathVariable("questionID") String questionID) {
		Response winnerResponse = responseService.getWinner(questionID, questionService.getQuestion(questionID));
		Employee winner = userService.findByEmailID(winnerResponse.getEmployeeEmail());
		OptionPercentage optionPercentage = responseService.getPercentages(questionID);
		return new QuizResult(optionPercentage, winnerResponse, winner);
	}

}
