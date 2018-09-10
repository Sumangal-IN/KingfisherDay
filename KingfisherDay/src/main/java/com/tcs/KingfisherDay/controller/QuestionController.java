package com.tcs.KingfisherDay.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@RequestMapping(value = "/setCurrentQuestion/{questionID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void setCurrentQuestion(@PathVariable("questionID") String questionID) {
		questionService.updateCurrentQuestion(questionID);
		messagingTemplate.convertAndSend("/topic/broadcastCurrentQuestion", questionService.getCurrentQuestion());
	}

	@RequestMapping(value = "/clearCurrentQuestion", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void clearCurrentQuestion() {
		questionService.clearCurrentQuestion();
		messagingTemplate.convertAndSend("/topic/broadcastCurrentQuestion", "{\"questionUnavailbleText\":true}");
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
		Question question = questionService.getQuestion(questionID);
		OptionPercentage optionPercentage = responseService.getPercentages(questionID);
		if (winnerResponse != null) {
			Employee winner = userService.findByEmailID(winnerResponse.getEmployeeEmail());
			return new QuizResult(optionPercentage, winner, question);
		}
		return new QuizResult(optionPercentage, question);
	}

	@RequestMapping(value = "/getCurrentQuestionAdmin", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Question getResult() {
		return questionService.getCurrentQuestion();
	}

	@MessageMapping("/getCurrentQuestion")
	public void sendMessage(Principal principal, @SuppressWarnings("rawtypes") Map message) {
		System.out.println("principal:" + principal.getName());
		if (questionService.getCurrentQuestion() != null)
			messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/getCurrentQuestion",
					questionService.getCurrentQuestion());
		else
			messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/getCurrentQuestion",
					"{\"questionUnavailbleText\":true}");
	}

}
