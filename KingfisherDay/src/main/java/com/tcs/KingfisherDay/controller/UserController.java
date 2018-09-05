package com.tcs.KingfisherDay.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// Save the uploaded file to this folder
	private static String UPLOAD_FOLDER = "C://img//";

	@RequestMapping(value = "/registerEmployee/{firstName}/{lastName}/{emailID}/{foodPreference}/{password}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Employee registerEmployee(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("emailID") String emailID,
			@PathVariable("foodPreference") String foodPreference, @PathVariable("password") String password,
			@RequestParam("photoFile") MultipartFile photoFile) {
		System.out.println("Controller");
		String filename = photoFile.getOriginalFilename() + System.currentTimeMillis() + Math.random() + ".jpg";
		if (photoFile.isEmpty()) {
			return null;
		}
		try {
			byte[] bytes = photoFile.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + filename);
			Files.write(path, bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userService.register(firstName, lastName, emailID, foodPreference, filename, password);
	}
}
