package com.tcs.KingfisherDay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.service.ImageHandlingService;
import com.tcs.KingfisherDay.service.UserService;

@Controller
public class UserController {


	@Autowired
	UserService userService;
	@Autowired
	ImageHandlingService imageHandlingService;

	@RequestMapping(value = "/registerEmployee/{name}/{emailID}/{foodPreference}/{password}/{mobile}/", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Employee registerEmployee(@PathVariable("name") String name, @PathVariable("emailID") String emailID,
			@PathVariable("foodPreference") String foodPreference, @PathVariable("password") String password,
			@PathVariable("mobile") String mobile, @RequestParam("photoFile") MultipartFile photoFile) throws Exception {
		
		Employee employee=userService.findByEmailID(emailID);
		
		if (null == employee) {
			try {
				String photo = imageHandlingService.resizeImage(photoFile);
				employee = userService.register(name, emailID, foodPreference, password, mobile, photo);
				System.out.println("Employee Registered:" + employee.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			throw new Exception("Oops!! Looks like user with this email id already exists!! Try to sign-in");
		}
		return employee;
	}

	@RequestMapping(value = "/getEmployee/{emailID}/{password}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Employee getEmployee(@PathVariable("emailID") String emailID, @PathVariable("password") String password) {
		return userService.getEmployee(emailID, password);
	}

	@RequestMapping(value = "/isExistsEmployee/{emailID}/{mobile}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Boolean isExistsEmployee(@PathVariable("emailID") String emailID, @PathVariable("mobile") String mobile) {
		return userService.isExistsEmployee(emailID, mobile);
	}
	

}
