package com.tcs.KingfisherDay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.service.UserService;
import com.tcs.KingfisherDay.util.ImageProcessor;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ImageProcessor imageProcessor;

	@RequestMapping(value = "/registerEmployee/{name}/{emailID}/{foodPreference}/{password}/{mobile}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Employee registerEmployee(@PathVariable("name") String name, @PathVariable("emailID") String emailID,
			@PathVariable("foodPreference") String foodPreference, @PathVariable("password") String password,
			@PathVariable("mobile") String mobile, @RequestParam("photoFile") MultipartFile photoFile) {
		String photo = null;
		if (photoFile.isEmpty()) {
			return null;
		}
		try {
			byte[] bytes = photoFile.getBytes();
			photo = imageProcessor.resizeImage(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userService.register(name, emailID, foodPreference, password, mobile, photo);
	}

	@RequestMapping(value = "/getEmployee/{emailID}/{password}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Employee getEmployee(@PathVariable("emailID") String emailID, @PathVariable("password") String password) {
		return userService.getEmployee(emailID, password);
	}

	@RequestMapping(value = "/isExistsEmployee/{emailID}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Boolean isExistsEmployee(@PathVariable("emailID") String emailID) {
		return userService.isExistsEmployee(emailID);
	}

}
